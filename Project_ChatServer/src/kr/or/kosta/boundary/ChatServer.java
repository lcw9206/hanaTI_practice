package kr.or.kosta.boundary;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import kr.or.kosta.bin.Client;
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
	public boolean isRunning() {
		return running;
	}

	public Hashtable<String, Client> getOutClients() {
		return outClients;
	}

	public Hashtable<String, Room> getRooms() {
		return rooms;
	}

	public String getRoomsList() {
		String roomsList = "";
		Room room = null;
		List list = new ArrayList();
		list.addAll(rooms.values());
		for (int i = 0; i < list.size(); i++) {
			room = (Room) list.get(i);
			if (room != null && room.getRoomTitle() != null) {
				roomsList += (room.getRoomTitle() + "\t");
			}
		}
		return roomsList;
	}

	public String getRoomsNumberList() {
		String roomsNumberList = "";
		Room room = null;
		List list = new ArrayList();
		list.addAll(rooms.values());
		for (int i = 0; i < list.size(); i++) {
			room = (Room) list.get(i);
			roomsNumberList += (room.getRoomNumber() + "\t");
		}
		return roomsNumberList;
	}

	public int getOutClientsCount() {
		return outClients.size();
	}

	public List getOutClientsList() {
		List list = new ArrayList();
		Enumeration<Client> e = outClients.elements();
		while (e.hasMoreElements()) {
			list.add(e.nextElement());
		}
		return list;
	}

	public int getJoinClientsCount(String roomTitle) {
		return rooms.get(roomTitle).getClientsCount();
	}

	public List getJoinClientsList(String roomTitle) {
		List list = new ArrayList();
		list.addAll(rooms.get(roomTitle).getClientsList());
		return list;
	}

	public HashMap getAllClientsList() {
		HashMap<String, Client> clientsList = new HashMap<>();
		List list = new ArrayList();
		List list2 = new ArrayList();
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

	public String outClientsString() {
		String result = "";
		Enumeration<String> e = outClients.keys();

		while (e.hasMoreElements()) {
			result += (e.nextElement() + "\t");
		}
		return result;
	}

	public String joinClientsString(String roomTitle) {
		String result = "";
		List list = null;
		Client client;
		Room room = rooms.get(roomTitle);
		list = room.getClientsList();
		for (int i = 0; i < list.size(); i++) {
			client = (Client) list.get(i);
			result += (client.getNickName() + "\t");
		}
		return result;
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
				e.printStackTrace();
			}
		}
	}

	public void shutDown() {

	}

	// 방장 추가
	public void addRoom(String nickName, String roomTitle, int maxPeople, Client client) {
		Room room = new Room(nickName, roomTitle, maxPeople);
		rooms.put(roomTitle, room);
	}

	public void addClient(Client client) {
		outClients.put(client.getNickName(), client);
	}

	public void removeClient(Client client, String roomTitle) {
		if (outClients.contains(client)) {
			outClients.remove(client.getNickName(), client);
		} else {
			rooms.get(roomTitle).getClientsList().remove(client);
		}
	}

	/**
	 * 
	 * @param roomTitle
	 * @param client
	 */
	public void joinRoom(String roomTitle, Client client) {
		rooms.get(roomTitle).addClientsList(client);
		System.out.println("들어온 방 : " + rooms.get(roomTitle).getRoomTitle() + " 인원 size : " + rooms.get(roomTitle).getClientsList().size());
		outClients.remove(client.getNickName(), client);
		System.out.println("대기실 인원 : " + outClients.size());
	}

	public void removeRoom(Room room) {
		rooms.remove(room.getRoomTitle(), room);
	}

	public void exitRoom(Client client, String roomTitle) {
//		조인, 아웃일 경우
//		List list = new ArrayList();
//		list.addAll(rooms.get(roomTitle).addClientsList(client));
		//		Client client1 = null;
//		getJoinClientsList(roomTitle).
//		 키(사람)가 없을 경우 방 삭제
//		if (joinClients.get(roomTitle)) {
//			rooms.remove(roomTitle);
//		}
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

	/**
	 * 귓속말 수행 메서드
	 * 
	 * @param message
	 */
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
		Client client;
		List<Client> list = new ArrayList();
		list.addAll(getJoinClientsList(roomTitle));
		for (int i = 0; i < list.size(); i++) {
			client = (Client) list.get(i);
			client.sendMessage(message);
		}
	}

	public boolean isExistNickName(String nickName) {

		return getAllClientsList().containsKey(nickName);
	}

}
