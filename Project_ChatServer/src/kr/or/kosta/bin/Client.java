package kr.or.kosta.bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import kr.or.kosta.boundary.ChatServer;
import kr.or.kosta.common.Protocol;
import kr.or.kosta.entity.Room;

/**
 * 클라이언트로부터 받은 정보를 관리하는 클래스
 * 
 * @author 이철우
 *
 */
public class Client extends Thread {
	/** 변수 목록 */
	private boolean running;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	private String nickName;
	private String roomTitle;
	private int maxPeople;
	
	ChatServer chatServer;
	

	/**
	 * Client 클래스 생성자
	 * 
	 * @param socket
	 * @param chatServer
	 * @throws IOException
	 */
	public Client(Socket socket, ChatServer chatServer) throws IOException {
		this.socket = socket;
		this.chatServer = chatServer;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		running = true;
	}

	/** 기본 get, set메서드 */
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Socket getSocket() {
		return socket;
	}
	
	/**
	 * 닉네임 변경 메서드
	 * 
	 * @param nickName
	 * @param changeNickName
	 * @param client
	 * @return boolean
	 */
	public boolean changeNickName(String nickName, String changeNickName, Client client) {
		// 전체 참여자 목록에서 변경할 닉네임을 검색해 중복여부를 판단합니다.
		if(chatServer.getAllClientsList().get(changeNickName) == null) {
			chatServer.removeOutClient(client);
			chatServer.addOutClient(client);
			return true;
		} else {
			return false;
		}
	}

	/**
	 *  클라이언트로부터 메세지를 받는 메서드
	 */
	public void recieveMessage() {
		while (running) {
			String clientMessage = null;
			try {
				clientMessage = in.readLine();
				process(clientMessage);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 클라이언트의 메시지를 파싱하여 서비스 제공하는 메서드
	 * 약속된 프로토콜에 따라 메세지를 받고, 보내줍니다.
	 * @param message
	 */
	public void process(String message) {
		String[] tokens = message.split(Protocol.DELEMETER);
		int protocol = Integer.parseInt(tokens[0]);
		nickName = tokens[1];
		
		switch (protocol) {
		
		// 최초 서버 연결
		case Protocol.CONNECT_SERVER:
			
			// 대화명 중복 여부 체크
			if (chatServer.isExistNickName(nickName)) {
				// 대화명 중복 시, 클라이언트에 FAIL 메세지 전송
				sendMessage(
						  Protocol.CONNECT_SERVER 
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_FAIL);
			} else {
				// 대화명이 중복되지 않을 경우 대기 인원에 클라이언트 추가
				chatServer.addOutClient(this);
				String waitingClients = chatServer.outClientsString();
				String roomsList = chatServer.getRoomsTitleList();
				
				// 메세지에 대기인원 및 대화방 목록 전송
				chatServer.sendOutMessage(
						  Protocol.CONNECT_SERVER 
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER
						+ Protocol.RESPONSE_SUCCESS 
						+ Protocol.DELEMETER 
						+ waitingClients 
						+ Protocol.DELEMETER
						+ roomsList);
			}
			break;

		// 방 추가 
		case Protocol.ADD_ROOM:
			roomTitle = tokens[2];
			maxPeople = Integer.parseInt(tokens[3]);
	
			// 방 이름 중복 여부 
			if (chatServer.getRooms().containsKey(roomTitle)) {
				// 중복 시, FAIL 메세지 
				sendMessage(
					      Protocol.ADD_ROOM_PARTICIPANT
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_FAIL
						+ Protocol.DELEMETER
						+ roomTitle);
			} else {				
				// 중복되지 않을 시, 방 생성
				chatServer.addRoom(nickName, roomTitle, maxPeople, this);
				
				// 방 생성을 알리기 위해 대기 인원과 방의 인원(자기 자신)에게 메세지를 보냅니다.
				chatServer.sendOutMessage(
						  Protocol.ADD_ROOM_WAITING
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_SUCCESS
						+ Protocol.DELEMETER 
						+ chatServer.getRoomsListString());
				
				chatServer.sendJoinMessage(
						  Protocol.ADD_ROOM_PARTICIPANT 
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_SUCCESS
						+ Protocol.DELEMETER 
						+ roomTitle
						, roomTitle);
				
			}
			break;

		// 방 접속
		case Protocol.JOIN_ROOM:
			roomTitle = tokens[2];
			// 방 접속을 위해 방을 관리하는 rooms에서 key(방 제목)으로 객체를 찾습니다.
			Room joinRoom = chatServer.getRooms().get(roomTitle);
			
			// 찾는 방이 없거나, 접속 인원이 가득 찼을 경우를 체크합니다.
			if (joinRoom != null && joinRoom.isEnterable()) {
				chatServer.joinRoom(roomTitle, this);
				// 방 접속 후, 대기 인원, 방의 인원, 자기 자신 모두에게 메세지를 전송합니다.
				chatServer.sendJoinMessage(
						  Protocol.JOIN_ROOM_PARTICIPANT_EXCEPT
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_SUCCESS
						+ Protocol.DELEMETER 
						+ roomTitle 
						+ Protocol.DELEMETER 
						+ chatServer.joinClientsString(roomTitle)
						, roomTitle);
				
				chatServer.sendOutMessage(
						  Protocol.JOIN_ROOM_WAITING 
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_SUCCESS
						+ Protocol.DELEMETER 
						+ roomTitle 
						+ Protocol.DELEMETER 
						+ chatServer.outClientsString());

				chatServer.sendPrivateMessage(
						  Protocol.JOIN_ROOM_PARTICIPANT
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_SUCCESS
						+ Protocol.DELEMETER 
						+ roomTitle
						+ Protocol.DELEMETER 
						+ chatServer.joinClientsString(roomTitle)
						, nickName);
			} else {
				// 접속 실패 시, 자신에게 메세지를 전송합니다.
				chatServer.sendPrivateMessage(
						  Protocol.JOIN_ROOM_PARTICIPANT 
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_FAIL
						+ Protocol.DELEMETER 
						+ roomTitle
						, nickName);
			}
			break;

		// 전체 채팅
		case Protocol.MULTI:
			roomTitle = tokens[2];
			// 메세지가 비어있는지를 체크합니다.
			if (message != null)
				chatServer.sendJoinMessage(message, roomTitle);
			break;

		// 귓속말
		case Protocol.PRIVATE:
			String receiveClient = tokens[4];
			// 메세지가 비어있는지를 체크합니다.
			if (message != null) {
				// 보내는 사람과 받는 사람 모두에게 메세지를 보냅니다.
				chatServer.sendPrivateMessage(message, nickName);
				chatServer.sendPrivateMessage(message, receiveClient);
			}
			break;

		// 초대
		case Protocol.INVITE:
			roomTitle = tokens[2];
			String inviteClient = tokens[3];
			
			// 초대 메세지를 보내기 전, 방에 입장이 가능한지 체크합니다.
			if(chatServer.getRooms().get(roomTitle).isEnterable()) {
				// 입장 가능 시, 성공 메세지를 보냅니다.
				chatServer.sendPrivateMessage(
						  Protocol.INVITE 
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ roomTitle 
						+ Protocol.DELEMETER
						+ inviteClient
						+ Protocol.DELEMETER
						+ Protocol.FLAG_TRUE
						, nickName);
			} else {
				chatServer.sendPrivateMessage(
						  Protocol.INVITE 
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ roomTitle 
						+ Protocol.DELEMETER
						+ inviteClient
						+ Protocol.DELEMETER
						+ Protocol.FLAG_FALSE
						, nickName);	
			}
			
			break;
		
		// 초대 확정
		case Protocol.INVITE_ACCEPT:
			roomTitle = tokens[2];
			// 초대받은 사람
			String invitedClient = tokens[3];
			
			// 초대할 방 목록
			Room room3 = chatServer.getRooms().get(roomTitle);
			// 대기 인원을 초대할 방에 추가
			room3.addClientsList(chatServer.getOutClients().get(invitedClient));
			// 이 후, 대기 인원 목록에서 삭제
			chatServer.getOutClients().remove(invitedClient);
		
			// 방 접속 후, 대기 인원, 방의 인원, 자기 자신 모두에게 메세지를 전송합니다.
			chatServer.sendPrivateMessage(
					  Protocol.INVITE_ACCEPT
					+ Protocol.DELEMETER 
					+ nickName 
					+ Protocol.DELEMETER 
					+ roomTitle 
					+ Protocol.DELEMETER
					+ invitedClient
					+ Protocol.DELEMETER
					+ chatServer.joinClientsString(roomTitle)
					, invitedClient);
			
			chatServer.sendOutMessage(
					  Protocol.INVITE_WAITING
					+ Protocol.DELEMETER 
					+ nickName 
					+ Protocol.DELEMETER 
					+ roomTitle 
					+ Protocol.DELEMETER
					+ invitedClient
					+ Protocol.DELEMETER
					+ chatServer.outClientsString());
			
			chatServer.sendJoinMessage(
					  Protocol.INVITE_PATICIPANT
					+ Protocol.DELEMETER 
					+ nickName 
					+ Protocol.DELEMETER 
					+ roomTitle 
					+ Protocol.DELEMETER
					+ invitedClient
					+ Protocol.DELEMETER
					+ chatServer.joinClientsString(roomTitle)
					, roomTitle);
			
			break;
			
		// 방 나가기
		case Protocol.EXIT_ROOM:			
			roomTitle = tokens[2];
			// 방을 나갈 클라이언트와 방 제목을 매개변수로 메서드에 전달합니다.
			chatServer.exitRoom(this, roomTitle);
			
			// 먼저 방을 나갔음을 자기 자신에게 알립니다. 
			chatServer.sendPrivateMessage(
					  Protocol.EXIT_ROOM 
					+ Protocol.DELEMETER 
					+ nickName 
					+ Protocol.DELEMETER 
					+ roomTitle 
					, nickName);
			
			// 이 후, 방이 존재할 경우와 존재하지 않는 경우로 나누어 메세지를 전달합니다.
			// 방 삭제는 exitRoom 메서드에서 진행합니다. 
			if(chatServer.getRooms().containsKey(roomTitle)) {
				chatServer.sendJoinMessage(
						  Protocol.EXIT_ROOM_PARTICIPANT
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_SUCCESS
						+ Protocol.DELEMETER 
						+ roomTitle 
						+ Protocol.DELEMETER 
						+ chatServer.joinClientsString(roomTitle)
						, roomTitle);
				
				chatServer.sendOutMessage(
						  Protocol.EXIT_ROOM_WAITING 
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_SUCCESS
						+ Protocol.DELEMETER 
						+ roomTitle 
						+ Protocol.DELEMETER 
						+ chatServer.outClientsString()
						+ Protocol.DELEMETER
						+ chatServer.getRoomsListString());
			} else {
				chatServer.sendJoinMessage(
						  Protocol.EXIT_ROOM_PARTICIPANT
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_SUCCESS
						+ Protocol.DELEMETER 
						+ roomTitle 
						, roomTitle);
				
				chatServer.sendOutMessage(
						  Protocol.EXIT_ROOM_WAITING 
						+ Protocol.DELEMETER 
						+ nickName 
						+ Protocol.DELEMETER 
						+ Protocol.RESPONSE_SUCCESS
						+ Protocol.DELEMETER 
						+ roomTitle 
						+ Protocol.DELEMETER 
						+ chatServer.outClientsString()
						+ Protocol.DELEMETER
						+ chatServer.getRoomsListString());
			}
			
			break;

		// 접속 종료
		case Protocol.DISCONNECT:
			// 접속 종료 위치 확인을 위한 변수
			String flag = tokens[2];
			running = false;
			
			// 방에서 접속을 종료했는지, 대기실에서 종료했는지를 체크합니다. 
			if(flag.equals("TRUE")) {
				String disConnectRoom = tokens[3];
				boolean roomExist;
				roomTitle = tokens[2];
				// 방의 인원을 삭제하며 이 후, 방의 존재 여부를 반환받습니다.
				roomExist = chatServer.removeRoomClient(this, disConnectRoom);
				// 방의 존재 여부에 따라 메세지를 다르게 보냅니다.
				if(roomExist) {
					chatServer.sendAllMessage(
							  Protocol.DISCONNECT 
							+ Protocol.DELEMETER 
							+ nickName
							+ Protocol.DELEMETER
							+ Protocol.FLAG_FALSE
							+ Protocol.DELEMETER
							+ disConnectRoom);
				} else {
					chatServer.sendAllMessage(
							  Protocol.DISCONNECT 
							+ Protocol.DELEMETER 
							+ nickName
							+ Protocol.DELEMETER
							+ Protocol.FLAG_TRUE
							+ Protocol.DELEMETER
							+ disConnectRoom);
				}
			} else {
				chatServer.removeOutClient(this);
				chatServer.sendAllMessage(
						  Protocol.DISCONNECT 
						+ Protocol.DELEMETER 
						+ nickName
						+ Protocol.DELEMETER
						+ Protocol.FLAG_FALSE);
			}
						
			break;

		// 방 정보
		case Protocol.ROOM_INFO:  
			roomTitle = tokens[2];
			Room room = chatServer.getRooms().get(roomTitle);
			// 특정 방의 정보를 전달합니다. 
			if (room != null) {
				chatServer.sendPrivateMessage(
						  Protocol.ROOM_INFO
						+ Protocol.DELEMETER
						+ nickName  
						+ Protocol.DELEMETER
						+ room.getRoomTitle()
						+ Protocol.DELEMETER
						+ room.getNickName()
						+ Protocol.DELEMETER
						+ room.getClientsList().size()
						+ Protocol.DELEMETER
						+ room.getMaxPeople()
						+ Protocol.DELEMETER
						+ chatServer.joinClientsString(roomTitle)
						, nickName);
				break;
			}
			break;
			
		// 특정 방 참여자 정보	
		case Protocol.PARTICIPANT_INFO:
			roomTitle = tokens[2];
			chatServer.sendPrivateMessage(
					  Protocol.PARTICIPANT_INFO
					+ Protocol.DELEMETER
					+ nickName 
					+ Protocol.DELEMETER
					+ chatServer.joinClientsString(roomTitle)
					, nickName); 
			break;
			 
		// 대기인원 정보	
		case Protocol.WAITING_INFO:
			chatServer.sendPrivateMessage(
					  Protocol.WAITING_INFO
					+ Protocol.DELEMETER
					+ nickName
					+ Protocol.DELEMETER
					+ chatServer.outClientsString()
					, nickName);
			break;
			
		// 닉네임 변경	
		case Protocol.CHANGE_NICKNAME:
			String changeNickName = tokens[2];
			// 변경할 닉네임의 존재 여부를 체크해 메세지를 분기합니다.
			if(changeNickName(nickName, changeNickName, this)) {
				chatServer.sendAllMessage(
						  Protocol.CHANGE_NICKNAME
						+ Protocol.DELEMETER
						+ nickName
						+ Protocol.DELEMETER
						+ Protocol.RESPONSE_SUCCESS
						+ Protocol.DELEMETER
						+ changeNickName);
				
				chatServer.sendPrivateMessage(
						  Protocol.CHANGE_NICKNAME_PARTICIPANT
						+ Protocol.DELEMETER
						+ nickName
						+ Protocol.DELEMETER
						+ Protocol.RESPONSE_SUCCESS
						+ Protocol.DELEMETER
						+ changeNickName
						, nickName);
				break;

			} else {
				chatServer.sendPrivateMessage(
						  Protocol.CHANGE_NICKNAME_PARTICIPANT
						+ Protocol.DELEMETER
						+ nickName
						+ Protocol.DELEMETER
						+ Protocol.RESPONSE_FAIL
						+ Protocol.DELEMETER
						+ changeNickName
						, nickName);
				break;
			}
			
		default:
			break;
		}
	}

	/**
	 * 메세지를 보내는 메서드
	 * 
	 * @param message
	 */
	public void sendMessage(String message) {
		out.println(message);
	}

	@Override
	public void run() {
		recieveMessage();
	}
}
