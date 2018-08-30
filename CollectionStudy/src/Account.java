/**
* 일상생활의 객체를 추상화하기 위한 모델링 클래스 정의
* 은행계좌 객체
*/
public class Account {
	
	// static 초기화 블럭 (특수한 목적의 명령어 실행)
	static {
		System.out.println("초기화 블럭 실행");
	}
	// 클래스(static) 변수 선언
	public final static String bankName = "하나은행";
	
	// 인스턴스 변수 선언
	// String은 클래스이므로 아래의 변수들은 참조변수가 된다.
	// String은 null로 초기화된다. => 참조하는 것이 없다는 것을 알리는 리터럴
	private String accountNum;
	private String accountOwner;
	private int passwd;
	private long restMoney;
	
	// default 생성자
	public Account(){
		this(null, null);
	}

	// 생성자
	public Account(String accountNum, String accountOwner){
		//this.accountNum = accountNum;
		//this.accountOwner = accountOwner;

		// this를 이용해 다른 생성자를 불러와 사용한다.
		// 생성자는 직접 호출할 수 없으므로 아래와 같이 this를 사용한다.
		this(accountNum, accountOwner, 0, 0);
	}

	// 생성자
	public Account(String accountNum, String accountOwner, int passwd, long restMoney){
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = restMoney;
	}
	
	// setter/getter 메소드
	public void setAccountNum(String accountNum){
		this.accountNum = accountNum;
	}

	public String getAccountNum(){
		return accountNum;
	}

	public void setAccountOwner(String accountOwner){
		this.accountOwner = accountOwner;
	}

	public String getAccountOwner(){
		return accountOwner;
	}

	public void setPasswd(int passwd){
		this.passwd = passwd;
	}

	public int getPasswd(){
		return passwd;
	}

	public void setRestMoney(int restMoney){
		this.restMoney = restMoney;
	}
	
	// 인스턴스 메소드
	public long deposit(long money) throws AccountException {
		
		if (money <= 0) {
			throw new AccountException("출금하고자 하고자 하는 금액은 음수일 수 없습니다.", -100);
		}
		this.restMoney += restMoney;
		return restMoney;
	}
	// 예외처리 
	public long withdraw(long money) throws AccountException {
		if(money <= 0) {
			// 강제 예외발생 후, throws로 호출한 곳에 리턴.
			throw new AccountException("출금하고자 하고자 하는 금액은 음수일 수 없습니다.", -1);
		} 
		if (money > restMoney) {
			throw new AccountException("잔액이 부족합니다.", -100);
		}
		
		this.restMoney -= restMoney;
		return this.restMoney;
	}

	public long getRestMoney(){
		return this.restMoney;
	}
	
	public boolean checkPasswd(int passwd){
		return this.passwd == passwd;
	}
	
	// 출력 메서드
	// 만들어진 문자열을 리턴해주므로써 브라우저, 스마트폰 등의 다양한 기계에 맞게 커스터마이징해 사용할 수 있다.
	public String toString() {
		return getAccountNum() + "\t" + getAccountOwner() + "\t" + "****" + "\t" + getRestMoney();
		
	}
	
	// 클래스 메서드
	public static int sum(int a, int b){
		return a + b;
	}
	
	// 위임형 비교
	// toString(). => 위의 toString()로 String 형으로 변환. 
	// 이 후, obj.toString()을 통해 객체를 String형으로 변환.
	// instanceof를 이용해 인스턴스 형을 비교
	@Override
	public boolean equals(Object obj) {
		boolean eq = false;
		if(obj instanceof Account) {
			eq = toString().equals(obj.toString());
		}
		return eq;
	}
	
	@Override
	public int hashCode() {
		// return 0;으로 무조건 hash값을 같게해 equals를 강제호출 할 수 있지만,
		// String 클래스의 hashCode 메서드를 재사용해 문제를 해결할 수 있다.
		return toString().hashCode();
	}
}
