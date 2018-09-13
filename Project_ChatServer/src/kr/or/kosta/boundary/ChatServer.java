package kr.or.kosta.boundary;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import kr.or.kosta.bin.Client;
import kr.or.kosta.common.Protocol;
import kr.or.kosta.entity.Room;

public class ChatServer {
	public static final int PORT = 7777;
	private boolean running;
	private ServerSocket serverSocket;
	// 밖에 있는 인원 (닉네임, 객체)
	private Hashtable<String, Client> outClients;
	// 방을 저장하는 객체 (방 제목, 객체)
	private Hashtable<String, Room> rooms;

	// getter 메서드

	public Hashtable<String, Client> getOutClients() {
		return outClients;
	}

	public Hashtable<String, Room> getRooms() {
		return rooms;
	}

	public String getRoomsTitleList() {
		String roomsTitleList = "";
		List<String> roomTitles = Collections.list(rooms.keys());
		for (String title : roomTitles) {
			roomsTitleList += title;
		}
		return roomsTitleList;
	}

	public List getRoomsList() {
		List<Room> roomsList = new ArrayList<Room>();
		roomsList.addAll(rooms.values());
		return roomsList;
	}
	
	public List<Client> getRoomClientsList(String roomTitle) {

		List<Client> clientList = new ArrayList<Client>();
		Room room = rooms.get(roomTitle);
		
		if (room != null) {
			clientList.addAll(room.getClientsList());
		}

		return clientList;
	}
	
	public List<Client> getOutClientsList() {
		List<Client> list = new ArrayList<Client>();
		Enumeration<Client> e = outClients.elements();
		while (e.hasMoreElements()) {
			list.add(e.nextElement());
		}
		return list;
	}
	
	public HashMap<String, Client> getAllClientsList() {
		HashMap<String, Client> clientsList = new HashMap<>();
		List<Room> list = new ArrayList<Room>();
		List<Client> list2 = new ArrayList<Client>();
		Room room = null;
		if (!rooms.values().isEmpty()) {
			list.addAll(rooms.values());

			for (int i = 0; i < list.size(); i++) {
				room = (Room) list.get(i);
				list2.addAll(room.getClientsList());
			}

			for (int i = 0; i < list2.size(); i++) {
				Client client = (Client) list2.get(i);
				clientsList.put(client.getNickName(), client);
			}
		}
		clientsList.putAll(outClients);

		return clientsList;
	}

	public int getRoomClientCount(String roomTitle) {
		return getRoomClientsList(roomTitle).size();
	}
	
	public int getOutClientsCount() {
		return outClients.size();
	}

	public String getRoomsListString() {
		String result = "";
		String key = "";
		Enumeration<String> e = rooms.keys();
		while (e.hasMoreElements()) {
			key = (String) e.nextElement();
			result += (key + Protocol.DELEMETER_SUB);
		}
		return result;
	}
	
	public String outClientsString() {
		String result = "";
		Enumeration<String> e = outClients.keys();

		while (e.hasMoreElements()) {
			result += (e.nextElement() + Protocol.DELEMETER_SUB);
		}
		return result;
	}

	public String joinClientsString(String roomTitle) {
		String result = "";
		Room room = null;
		room = rooms.get(roomTitle);
		List<Client> list = room.getClientsList(); 
		for (Client client : list) {
			result += (client.getNickName() + Protocol.DELEMETER_SUB);
		}
		return result;
	}
	
	public boolean isRunning() {
		return running;
	}

	public void startUp() throws IOException {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (Exception e) {
			throw new IOException("[" + PORT + "] 포트 충돌로 ChatServer를 구동할 수 없습니다.");
		}

		running = true;
		outClients = new Hashtable<String, Client>();
		rooms = new Hashtable<String, Room>();

		System.out.println("BTS[" + PORT + "] ChatServer Start....");

		while (running) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("connect from [" + socket.getInetAddress() + "]");
				Client client = new Client(socket, this);
				client.start();

			} catch (IOException e) {
				break;
			}
		}
	}

	// 방 추가
	public void addRoom(String nickName, String roomTitle, int maxPeople, Client client) {
		Room room = new Room(nickName, roomTitle, maxPeople);
		room.addClientsList(client);
		rooms.put(roomTitle, room);
		outClients.remove(client.getNickName());
	}

	public void addOutClient(Client client) {
		outClients.put(client.getNickName(), client);
	}
	
	// 오버로딩 방에 있는 애들 삭제
	public boolean removeClient(Client client, String roomTitle) {
		Room room = rooms.get(roomTitle);			
		room.removeClientsList(client);
		
		if (room.getClientsCount() == 0) {
			rooms.remove(roomTitle);
			return false;
		}
		return true;
	}

	public void removeClient(Client client) {
		outClients.remove(client.getNickName(), client);
	}

	/**
	 * 
	 * @param roomTitle
	 * @param client
	 */
	public void joinRoom(String roomTitle, Client client) {
		rooms.get(roomTitle).addClientsList(client);
		outClients.remove(client.getNickName());
		
		System.out.println("들어온 방 : " + rooms.get(roomTitle).getRoomTitle() + 
				" 방 인원 : " + rooms.get(roomTitle).getClientsList().size() + 
				" 벡터 사이즈 : " + rooms.get(roomTitle).getClientsCount() +
				" 입장한 사람들 : " + joinClientsString(roomTitle)+ "\n");
	}

	public void removeRoom(Room room) {
		rooms.remove(room.getRoomTitle(), room);
	}

	public void exitRoom(Client client, String roomTitle) {
//		조인, 아웃일 경우
		Room room = rooms.get(roomTitle);
		outClients.put(client.getNickName(), client);
		if (room != null) {
			room.removeClientsList(client);
		}

		System.out.println("방안의 인원 : " + getRoomClientCount(roomTitle) + " 밖의 인원 : " + getOutClientsCount() + "\n");
		if (getRoomClientCount(roomTitle) == 0) {
			rooms.remove(roomTitle);
		}
	}

	public void sendAllMessage(String message) {
		Client client;
		List<Client> list = new ArrayList();
		list.addAll(getAllClientsList().values());
		for (int i = 0; i < list.size(); i++) {
			client = (Client) list.get(i);
			client.sendMessage(message);
		}
	}

	public void sendPrivateMessage(String message, String nickName) {
		Client client = (Client) getAllClientsList().get(nickName);
		client.sendMessage(message);
	}

	public void sendOutMessage(String message) {
		Enumeration<Client> e = outClients.elements();
		Client client = null;
		while(e.hasMoreElements()) {
			client = e.nextElement();
			client.sendMessage(message);
		}
	}

	public void sendJoinMessage(String message, String roomTitle) {
		Client client = null;
		List<Client> list = new ArrayList<Client>();
		
		list.addAll(getRoomClientsList(roomTitle));
		for (int i = 0; i < list.size(); i++) {
			client = (Client) list.get(i);
			client.sendMessage(message);
		}
	}

	public boolean isExistNickName(String nickName) {
		return getAllClientsList().containsKey(nickName);
	}

}
