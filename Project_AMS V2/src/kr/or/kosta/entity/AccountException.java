package kr.or.kosta.entity;

import javax.swing.JOptionPane;

public class AccountException extends Exception {

	private int errorCode;

	// Default 생성자
	public AccountException() {
		this("계좌처리 중 예기치 않은 에러가 발생했습니다.", -9);
	}

	// 생성자를 이용한 dialog 생성
	public AccountException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
		if (errorCode == 0) {
			JOptionPane.showMessageDialog(null, message, "알림", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, message, "알림", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return "AccountException [errorCode=" + errorCode + ", getMessage()=" + getMessage() + "]";
	}

}
