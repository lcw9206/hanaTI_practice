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
	public static final int JOIN_ROOM = 1002;

	// 채팅관련 프로토콜
	public static final int MULTI = 2001;
	public static final int PRIVATE = 2002;
	
	// 초대 프로코톨
	public static final int INVITE_ROOM = 3001;

	// 퇴장관련 프로토콜
	public static final int EXIT_ROOM = 4001;
	public static final int DISCONNECT = 4002;
	
}