package kr.or.kosta.entity;

public class AccountException extends Exception {
	
	private int errorCode;

	// Default 생성자
	public AccountException() {
		this("계좌처리 중 예기치 않은 에러가 발생했습니다.", -9);
	}

	public AccountException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return "AccountException [errorCode=" + errorCode + ", getMessage()=" + getMessage() + "]";
	}

}
