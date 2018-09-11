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
	private ServerSocket serverSocket;
	// 밖에 있는 인원 (닉네임, 객체)
	private Hashtable<String, Client> outClients;
	// 방에 들어가 있는 인원을 저장하는 객체 (방 제목, 객체) 
	private Hashtable<String, Client> joinClients;
	// 방을 저장하는 객체 (방 제목, 객체)
	private Hashtable<String, Room> rooms;

	Room room;
	
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

	public Hashtable<String, Client> getJoinClients() {
		return joinClients;
	}

	public int getOutCount() {
		return outClients.size();
	}

	public int getJoinCount() {
		return joinClients.size();
	}
	
	public int getRoomClients() {
		Enumeration<Room> e = getRooms().elements();
		return 0;
	}

	public String outClientsString() {
		String result = "";
		Enumeration<String> e = outClients.keys();

		while (e.hasMoreElements()) {
			result += (e.nextElement() + "\t");
		}
		return result;
	}
	
//	public String joinClientsString(String roomTitle) {
//		String result = "";
//		// 여기 기
//		Enumeration<String> e = outClients.keys();
//		Enumeration<String> e = joinClients.keys();
//
//		while (e.hasMoreElements()) {
//			result += (e.nextElement() + "\t");
//		}
//		return result;
//	}	

	public void startUp() throws IOException {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (Exception e) {
			throw new IOException("[" + PORT + "] 포트 충돌로 ChatServer를 구동할 수 없습니다.");
		}

		running = true;
		outClients = new Hashtable<String, Client>();
		rooms = new Hashtable<String, Room>();
		joinClients = new Hashtable<String, Client>();

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
	public void addRoom(String nickName, String roomTitle, int maxPeople) {
			Room room = new Room(nickName, roomTitle, maxPeople);
			rooms.put(roomTitle, room);
			joinRoom(roomTitle, getOutClients().get(nickName));
	}

	public void addClient(Client client) {
		outClients.put(client.getNickName(), client);
	}

	public void removeClient(Client client) {
		if (outClients.contains(client)) {
			outClients.remove(client.getNickName(), client);
		} else {
			joinClients.remove(client.getNickName(), client);
		}
	}

	/**
	 * 
	 * @param roomTitle
	 * @param client
	 */
	public void joinRoom(String roomTitle, Client client) {
		joinClients.put(roomTitle, client);
		outClients.remove(client.getNickName(), client);
	}

	public void removeRoom(Room room) {
		rooms.remove(room.getRoomTitle(), room);
	}

	public void exitRoom(Client client, String roomTitle) {
		// 조인, 아웃일 경우
		joinClients.remove(roomTitle, client);
		// 키(사람)가 없을 경우 방 삭제
		if (joinClients.get(roomTitle) == null) {
			rooms.remove(roomTitle);
		}
	}

	public void sendAllMessage(String message) {
		Enumeration<Client> e = outClients.elements();
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
		Client cl = joinClients.get(nickName);
		cl.sendMessage(message);
	}

	public boolean isExistNickName(String nickName) {

		return outClients.containsKey(nickName) || joinClients.contains(nickName);
	}

}
