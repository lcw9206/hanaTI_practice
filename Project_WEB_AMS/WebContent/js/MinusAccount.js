/**
 * JavaScript를 이용한 MinusAccount 클래스 구현
 */

// 마이너스 계좌 생성자
function MinusAccount(accountNum, accountOwner, passwd, restMoney, borrowMoney) {
	Account.call(this, accountNum, accountOwner, passwd, restMoney);
	this.borrowMoney = borrowMoney;
}

// Account 생성자 상속
MinusAccount.prototype = new Account();

// Prototype 객체에 메서드 생성

// 잔액 확인 메서드
MinusAccount.prototype.getRestMoney = function() {
	return this.restMoney - this.borrowMoney;
}

// 마이너스 계좌 정보 출력 메서드
MinusAccount.prototype.toString = function() {
	return '마이너스계좌\t' + this.accountNum + '\t' + this.accountOwner + '\t'
			+ this.restMoney + '\t' + this.borrowMoney;
}

MinusAccount.prototype.constructor = MinusAccount;