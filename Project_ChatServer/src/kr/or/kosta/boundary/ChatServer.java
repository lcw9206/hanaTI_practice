package kr.or.kosta.boundary;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import kr.or.kosta.bin.Client;
import kr.or.kosta.common.Protocol;
import kr.or.kosta.entity.Room;

/**
 * 방과 인원을 관리하는 클래스 
 * 인원을 방에서 관리해야 했습니다.
 * 
 * @author 이철우
 *
 */
public class ChatServer {
	/** 포트번호 */
	public static final int PORT = 7777;
	/** 스레드 동작 여부를 위한 변수 */
	private boolean running;

	private ServerSocket serverSocket;
	/** 대기방 인원 <닉네임, 객체> */
	private Hashtable<String, Client> outClients;
	/** 방 목록 <방 제목, 객체> */
	private Hashtable<String, Room> rooms;

	/**
	 * 대기 인원들을 반환하는 Getter 메서드
	 * 
	 * @return Hashtable<String, Client>
	 */
	public Hashtable<String, Client> getOutClients() {
		return outClients;
	}

	/**
	 * 생성된 방들을 반환하는 Getter 메서드
	 * 
	 * @return Hashtable<String, Room>
	 */
	public Hashtable<String, Room> getRooms() {
		return rooms;
	}

	/**
	 * 개설된 방들의 제목을 String 타입에 저장해 반환하는 Getter 메서드
	 * 
	 * @return String
	 */
	public String getRoomsTitleList() {
		String roomsTitleList = "";
		List<String> roomTitles = Collections.list(rooms.keys());
		for (String title : roomTitles) {
			roomsTitleList += title;
		}
		return roomsTitleList;
	}

	/**
	 * rooms 객체의 value(방 객체)들을 List에 담아 반환하는 Getter 메서드
	 * 
	 * @return List<Room>
	 */
	public List<Room> getRoomsList() {
		List<Room> roomsList = new ArrayList<Room>();
		roomsList.addAll(rooms.values());
		return roomsList;
	}

	/**
	 * 특정 대화방 안의 인원들을 List에 담아 반환하는 Getter 메서드
	 * 
	 * @param roomTitle
	 * @return List<Client>
	 */
	public List<Client> getJoinClientsList(String roomTitle) {
		List<Client> clientList = new ArrayList<Client>();
		Room room = rooms.get(roomTitle);

		if (room != null) {
			clientList.addAll(room.getClientsList());
		}

		return clientList;
	}

	/**
	 * 대기 인원들을 List에 담아 반환하는 Getter 메서드
	 * 
	 * @return List<Client>
	 */
	public List<Client> getOutClientsList() {
		List<Client> list = new ArrayList<Client>();
		Enumeration<Client> e = outClients.elements();
		while (e.hasMoreElements()) {
			list.add(e.nextElement());
		}
		return list;
	}

	/**
	 * 대기 인원, 접속 인원 모두를 Hashtalbe에 담아 반환하는 Getter 메서드 
	 * 동기화 처리를 위해 Hashtable 사용
	 * 
	 * @return Hashtable<String, Client>
	 */
	public Hashtable<String, Client> getAllClientsList() {
		Room room = null;
		Hashtable<String, Client> clientsList = new Hashtable<>();
		// 룸 객체들을 받을 list 선언
		List<Room> roomList = new ArrayList<Room>();
		// 룸 객체들의 접속 인원을 받을 list2
		List<Client> roomClients = new ArrayList<Client>();

		if (!rooms.values().isEmpty()) {
			roomList.addAll(rooms.values());
			// room 객체들 저장
			for (int i = 0; i < roomList.size(); i++) {
				room = (Room) roomList.get(i);
				roomClients.addAll(room.getClientsList());
			}
			// room 객체들의 clients 저장
			for (int i = 0; i < roomClients.size(); i++) {
				Client client = (Client) roomClients.get(i);
				clientsList.put(client.getNickName(), client);
			}
		}
		// 대기 인원 저장
		clientsList.putAll(outClients);

		return clientsList;
	}

	/**
	 * 특정 대화방 접속 인원수를 반환하는 메서드
	 * 
	 * @param roomTitle
	 * @return String
	 */
	public int getJoinClientCount(String roomTitle) {
		return getJoinClientsList(roomTitle).size();
	}

	/**
	 * 대기 인원수를 반환하는 메서드
	 * 
	 * @return int
	 */
	public int getOutClientsCount() {
		return outClients.size();
	}

	/**
	 * 개설된 방들의 제목을 나열한 String을 반환하는 메서드
	 * 
	 * @return String
	 */
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

	/**
	 * 대기실에 있는 인원들을 나열한 String을 반환하는 메서드
	 * 
	 * @return String
	 */
	public String outClientsString() {
		String result = "";
		Enumeration<String> e = outClients.keys();

		while (e.hasMoreElements()) {
			result += (e.nextElement() + Protocol.DELEMETER_SUB);
		}
		return result;
	}

	/**
	 * 특정 대기실의 인원들을 나열한 String을 반환하는 메서드
	 * 
	 * @param roomTitle
	 * @return String
	 */
	public String joinClientsString(String roomTitle) {
		String result = "";
		Room room = null;
		room = rooms.get(roomTitle);
		List<Client> list = new ArrayList<Client>();
		list = room.getClientsList();
		for (Client client : list) {
			result += (client.getNickName() + Protocol.DELEMETER_SUB);
		}
		return result;
	}

	/**
	 * 스레드의 동작 여부를 판단하는 메서드
	 * 
	 * @return boolean
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * 서버 접속 시, 소켓을 연결하고 Client 객체를 생성하는 메서드
	 * 
	 * @throws IOException
	 */
	public void startUp() throws IOException {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (Exception e) {
			throw new IOException("[" + PORT + "] 포트 충돌로 ChatServer를 구동할 수 없습니다.");
		}

		running = true;
		// 대기실 인원, 대화방들을 관리하기 위한 해쉬테이블
		outClients = new Hashtable<String, Client>();
		rooms = new Hashtable<String, Room>();

		while (running) {
			try {
				Socket socket = serverSocket.accept();
				Client client = new Client(socket, this);
				client.start();

			} catch (IOException e) {
				break;
			}
		}
	}

	/**
	 * 방 생성 메서드
	 * 
	 * @param nickName
	 * @param roomTitle
	 * @param maxPeople
	 * @param client
	 */
	public void addRoom(String nickName, String roomTitle, int maxPeople, Client client) {
		// 방 생성
		Room room = new Room(nickName, roomTitle, maxPeople);
		// 방을 생성한 참가자를 방의 인원에 추가
		room.addClientsList(client);
		// 방들을 관리하는 해쉬테이블에 방 추가
		rooms.put(roomTitle, room);
		// 방을 만든 인원을 대기인원에서 제거
		outClients.remove(client.getNickName());
	}

	/**
	 * 대기 인원 추가 메서드
	 * 
	 * @param client
	 */
	public void addOutClient(Client client) {
		outClients.put(client.getNickName(), client);
	}

	/**
	 * 대화방 인원 삭제 메서드
	 * 
	 * @param client
	 * @param roomTitle
	 * @return boolean
	 */
	public boolean removeRoomClient(Client client, String roomTitle) {
		Room room = rooms.get(roomTitle);
		room.removeClientsList(client);
		// 대화방 인원 삭제 후, 참여 인원이 없을 경우 room을 삭제합니다.
		if (room.getClientsCount() == 0) {
			rooms.remove(roomTitle);
			return false;
		}
		return true;
	}

	/**
	 * 대기 인원 삭제 메서드
	 * 
	 * @param client
	 */
	public void removeOutClient(Client client) {
		outClients.remove(client.getNickName(), client);
	}

	/**
	 * 대화방 참가 메서드 
	 * 참여자를 대화방에 추가 후, 대기 인원에서 삭제합니다.
	 * 
	 * @param roomTitle
	 * @param client
	 */
	public void joinRoom(String roomTitle, Client client) {
		rooms.get(roomTitle).addClientsList(client);
		outClients.remove(client.getNickName());
	}

	/**
	 * 대화방 삭제 메서드
	 * 
	 * @param room
	 */
	public void removeRoom(Room room) {
		rooms.remove(room.getRoomTitle(), room);
	}

	/**
	 * 대화방 나가기 메서드
	 * 
	 * @param client
	 * @param roomTitle
	 */
	public void exitRoom(Client client, String roomTitle) {
		// 참여자가 속한 방을 가져옵니다.
		Room room = rooms.get(roomTitle);
		// 대기 인원에 인원을 추가합니다.
		outClients.put(client.getNickName(), client);

		// 이 후, 방이 존재하면 방의 참여 목록에서 참여자를 삭제합니다.
		if (room != null) {
			room.removeClientsList(client);
		}

		// 방에 속한 사람이 없을 경우, 방을 삭제합니다.
		if (getJoinClientCount(roomTitle) == 0) {
			rooms.remove(roomTitle);
		}
	}

	/**
	 * 전체 인원 메세지 메서드
	 * 
	 * @param message
	 */
	public void sendAllMessage(String message) {
		Client client;
		List<Client> list = new ArrayList<Client>();
		// 전체 인원들의 목록을 list에 담습니다.
		list.addAll(getAllClientsList().values());
		for (int i = 0; i < list.size(); i++) {
			client = (Client) list.get(i);
			client.sendMessage(message);
		}
	}

	/**
	 * 귓속말 메서드
	 * 
	 * @param message
	 * @param nickName
	 */
	public void sendPrivateMessage(String message, String nickName) {
		Client client = (Client) getAllClientsList().get(nickName);
		client.sendMessage(message);
	}

	/**
	 * 대기인원에게만 메세지를 보내는 메서드
	 * 
	 * @param message
	 */
	public void sendOutMessage(String message) {
		Enumeration<Client> e = outClients.elements();
		Client client = null;
		
		// 해쉬테이블에 있는 대기인원들에게 메세지를 보냅니다.
		while (e.hasMoreElements()) {
			client = e.nextElement();
			client.sendMessage(message);
		}
	}

	/**
	 * 대화방 참여 인원에게만 메세지를 보내는 메서드
	 * 
	 * @param message
	 * @param roomTitle
	 */
	public void sendJoinMessage(String message, String roomTitle) {
		Client client = null;
		List<Client> list = new ArrayList<Client>();

		// 특정 방의 인원을 list에 담습니다.
		list.addAll(getJoinClientsList(roomTitle));
		for (int i = 0; i < list.size(); i++) {
			client = (Client) list.get(i);
			client.sendMessage(message);
		}
	}

	/**
	 * 중복 닉네임 검사 메서드
	 * 
	 * @param nickName
	 * @return boolean
	 */
	public boolean isExistNickName(String nickName) {
		return getAllClientsList().containsKey(nickName);
	}

}
