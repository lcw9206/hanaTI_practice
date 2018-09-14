package kr.or.kosta.bin;

import java.io.IOException;

import kr.or.kosta.boundary.ChatServer;

/**
 * 실행을 위한 애플리케이션 클래스
 * 
 * @author 이철우
 *
 */
public class BTS {

	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();
		try {

			chatServer.startUp();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
