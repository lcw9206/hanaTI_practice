package kr.or.kosta.entity;

public class AccountException extends Exception {
	// String message; => Exception에서 상속받아지기에 재사용 가능.
	// 메세지 저장을 위한 변수 선언이 필요없다.
	private int errorCode;

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

	// 상속
	@Override
	public String toString() {
		return "AccountException [errorCode=" + errorCode + ", getMessage()=" + getMessage() + "]";
	}

}
