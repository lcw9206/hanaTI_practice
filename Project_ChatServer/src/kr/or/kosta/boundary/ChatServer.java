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

	public List<Client> getClientFromRoom(String roomTitle) {

		List<Client> clientList = new ArrayList<Client>();
		Room room = rooms.get(roomTitle);

		if (room != null) {
			clientList.addAll(room.getClientsList());
		}

		return clientList;
	}

	public String getRoomsTitleList() {
		String roomsTitleList = "";
//		Room room = null;
//		List list = new ArrayList();
//		list.addAll(rooms.values());
//		for (int i = 0; i < list.size(); i++) {
//			room = (Room) list.get(i);
//			if (room != null && room.getRoomTitle() != null) {
//				roomsTitleList += (room.getRoomTitle() + "\t");
//			}
//		}
		List<String> roomTitles = Collections.list(rooms.keys());
		for (String title : roomTitles) {
			roomsTitleList += title;
		}
		return roomsTitleList;
	}

	public List getRoomsList() {
		List roomsList = new ArrayList();
		roomsList.addAll(rooms.values());
		return roomsList;
	}
	
	public List getOutClientsList() {
		List list = new ArrayList();
		Enumeration<Client> e = outClients.elements();
		while (e.hasMoreElements()) {
			list.add(e.nextElement());
		}
		return list;
	}

	public int getClientCountFromRoom(String roomTitle) {
		return getClientFromRoom(roomTitle).size();
	}

	public List getJoinClientsList(String roomTitle) {
//		List list = new ArrayList();
//		if(hasRoom(roomTitle)) {
//			list.addAll(rooms.get(roomTitle).getClientsList());
//		}
		return getClientFromRoom(roomTitle);
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

	public int getOutClientsCount() {
		return outClients.size();
	}

	public int getJoinClientsCount(String roomTitle) {
//		return rooms.get(roomTitle).getClientsCount();
		return getClientCountFromRoom(roomTitle);
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
		List list = getClientFromRoom(roomTitle);
		Client client;
//		if (hasRoom(roomTitle)) {
//			list = rooms.get(roomTitle).getClientsList();
			for (int i = 0; i < list.size(); i++) {
				client = (Client) list.get(i);
				result += (client.getNickName() + "\t");
			}
//		}
		return result;
	}

	public String getRoomsListString() {
		String result = "";
		Enumeration<String> e = getRooms().keys();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.println("elements : " + key);
			result += (key + "\t");
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
				break;
//				e.printStackTrace();
			}
		}
	}

	public void shutDown() {

	}

	// 방장 추가
	public void addRoom(String nickName, String roomTitle, int maxPeople, Client client) {
		Room room = new Room(nickName, roomTitle, maxPeople);
		rooms.put(roomTitle, room);
		joinRoom(roomTitle, client);

	}

	public void addClient(Client client) {
		outClients.put(client.getNickName(), client);
	}

	public void removeClient(Client client, String roomTitle) {
			Room room = rooms.get(roomTitle);			
			if (room != null) {
				room.removeClientsList(client);
			}
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
		outClients.remove(client.getNickName(), client);
		System.out.println("들어온 방 : " + rooms.get(roomTitle).getRoomTitle() + " 인원 size : "
				+ rooms.get(roomTitle).getClientsList().size() + " 대기실 인원 : " + outClients.size());
	}

	public void removeRoom(Room room) {
		rooms.remove(room.getRoomTitle(), room);

	}

	public void exitRoom(Client client, String roomTitle) {
//		조인, 아웃일 경우
//		rooms.get(roomTitle).removeClientsList(client);
//		getClientFromRoom(roomTitle).remove(client);
		Room room = rooms.get(roomTitle);
		if (room != null) {
			room.removeClientsList(client);
		}
		for (int i = 0; i < getClientFromRoom(roomTitle).size(); i++) {
			System.out.println("사람들 : " + getClientFromRoom(roomTitle).get(i).getNickName());
		}
		System.out.println("=========================");
		outClients.put(client.getNickName(), client);
		
//		if (rooms.get(roomTitle).getClientsCount() == 0)
		System.out.println("사이즈 : " + getClientCountFromRoom(roomTitle));
		if (getClientCountFromRoom(roomTitle) == 0) {
			System.out.println("방 삭제!");
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
