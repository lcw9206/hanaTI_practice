package kr.or.kosta.chat.common;

public interface Protocol {

	// 프로토콜이 늘어날 경우 데이터 흐름을 해쉬태그처럼 붙여주면 좋다.
	public static final String DELEMETER = ",";
	
	public static final int CONNECT = 1000;
	public static final int CONNECT_RESULT = 1001;
	public static final int MULTI_CHAT = 2000;
	public static final int DISCONNECT = 3000;
}
