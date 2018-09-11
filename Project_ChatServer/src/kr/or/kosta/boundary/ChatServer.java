package kr.or.kosta.boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

import kr.or.kosta.bin.Client;
import kr.or.kosta.entity.Room;

public class ChatServer {
	public static final int PORT = 7777;
	private boolean running;
	private String nickName;
	private Socket socket;
	private ServerSocket serverSocket;
	// 총 인원
	private Hashtable<String, Client> clients;
	// 방에 들어가 있는 인원
	private Hashtable<String, Client> joinClients;
	// 방의 수
	private Hashtable<String, Room> rooms;
	private PrintWriter out;
	private BufferedReader in;

	// getter 메서드
	public boolean isRunning() {
		return running;
	}

	public Hashtable<String, Client> getClients() {
		return clients;
	}

	public Hashtable<String, Room> getRooms() {
		return rooms;
	}

	public Hashtable<String, Client> getJoinCleints() {
		return joinClients;
	}
	
	public int getClientCount() {
		return clients.size();
	}

	public int getRoomClients() {
		return 0;
	}

	public void startUp() throws IOException {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (Exception e) {
			throw new IOException("[" + PORT + "] 포트 충돌로 ChatServer를 구동할 수 없습니다.");
		}

		running = true;
		clients = new Hashtable<String, Client>();
		rooms = new Hashtable<String, Room>();
		joinClients = new Hashtable<String, Client>();

		System.out.println("BTS[" + PORT + "] ChatServer Start....");

		while (running) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("connect from [" + socket.getInetAddress()+"]");
				Client client = new Client(socket, this);
				System.out.println("소켓 생성");
				client.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void shutDown() {

	}

	public void addRoom(String roomTitle, int maxPeople) {
		Room room = new Room(roomTitle, maxPeople);
		rooms.put(roomTitle, room);
	}

	public void addClient(Client client) {
		clients.put(client.getNickName(), client);
	}

	public void removeClient(Client client) {
		clients.remove(client.getNickName(), client);
	}
	
	/**
	 * joinRoom
	 * @param roomTitle
	 * @param client
	 */
	public void joinRoom(String roomTitle, int maxPeople, Client client) {
		if (!rooms.containsKey(roomTitle)) {
			System.out.println("방을 생성합니다!");
			addRoom(roomTitle, maxPeople);
		}
		System.out.println("join Room!");
		joinClients.put(roomTitle, client);
	}

	public void removeRoom(Room room) {
		rooms.remove(room.getRoomTitle(), room);
	}

	public void exitRoom(Client client, String roomTitle) {
		joinClients.remove(roomTitle, client);
		// 다시 생각해보기
		if(joinClients.get(roomTitle) == null) {
			rooms.remove(roomTitle);
		}
	}

	public void sendAllMessage(String message) {
		Enumeration<Client> e = joinClients.elements();
		while (e.hasMoreElements()) {
			Client client = e.nextElement();
			client.sendMessage(message);
		}
	}

	/**
	 * 귓속말 수행 메서드
	 * 
	 * @param message
	 */
	public void sendPrivateMessage(String message, String nickName) {
		Client cl = clients.get(nickName);
		cl.sendMessage(message);
	}

	public boolean isExistNickName(String nickName) {
		return clients.containsKey(nickName);
	}

}
