/**
* 프로그램 실행을 위한 애플리케이션 클래스 정의
*/
class AccountExample {
	
	public static void main(String[] args) {
		Account account;
		account = new Account("1111-2222-3333", "방그리", 1234, 100000);

		int passwd = 1234;
		boolean result = account.checkPasswd(passwd);

		if (result){
			long money = 0;
			try {
				money = account.deposit(100000);
				System.out.println("입금 후 잔액: "+ money);
//				money = account.deposit(-50000);
//				System.out.println("입금 후 잔액: "+ money);
				
				money = account.withdraw(10000000);
				System.out.println("출금 후 잔액 " + money);
			} catch (AccountException e) {
				System.out.println(e.getMessage());
			}
			
			
		}else {
			System.out.println("비밀번호를 확인하세요.");
		}

		Account account2 = new Account();
		// 인스턴스 변수의 경우 JVM에 의해 자동 초기화됨.
		account2.setAccountNum("1111-2222-3333");

		System.out.println(account2.getAccountNum());
		System.out.println(account2.getAccountOwner());
		System.out.println(account2.getRestMoney());
		System.out.println(account2.getPasswd());

		Account account3 = new Account("2222-3333-4444", "빵빵앙");

		// 클래스 변수 호출
		System.out.println(Account.bankName);
		// 클래스 메서드 호출
		System.out.println(Account.sum(1, 2));
		
		System.out.println("은행 계좌 애플리케이션 종료");
	}
}