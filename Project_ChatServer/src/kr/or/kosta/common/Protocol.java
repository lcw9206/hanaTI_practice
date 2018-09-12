package kr.or.kosta.common;

/**
 * 채팅을 위한 프로토콜 생성 
 * @author 이철우
 *
 */
public interface Protocol {

	public static final String DELEMETER = ",";

	// 접속관련 프로토콜
	public static final int CONNECT_SERVER = 1001;
	public static final int ADD_ROOM = 1002;
	public static final int ADD_ROOM_WAITING = 1003;
	public static final int ADD_ROOM_PARTICIPANT = 1004;
	public static final int JOIN_ROOM = 1005;
	public static final int JOIN_ROOM_WAITING = 1006;
	public static final int JOIN_ROOM_PARTICIPANT = 1007;
	

	// 채팅관련 프로토콜
	public static final int MULTI = 2001;
	public static final int PRIVATE = 2002;
	
	// 초대 프로코톨
	public static final int INVITE = 3001;
	public static final int INVITE_ACCEPT = 3002;
	public static final int INVITE_PATICIPANT = 3003;

	// 퇴장관련 프로토콜
	public static final int EXIT_ROOM = 4000;
	public static final int EXIT_ROOM_WAITING = 4001;
	public static final int EXIT_ROOM_PARTICIPANT = 4002;
	public static final int DISCONNECT = 4003;
	
	public static final int ROOM_INFO = 5001;
	public static final int PARTICIPANT_INFO = 5002;
	public static final int WAITING_INFO = 5003;
	
	public static final String RESPONSE_SUCCESS = "SUCCESS";
	public static final String RESPONSE_FAIL = "FAIL";

}