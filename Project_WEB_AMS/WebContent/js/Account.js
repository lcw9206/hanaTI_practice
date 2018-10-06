/**
 * JavaScript를 이용한 Account 클래스 구현
 */

// 입출금 계좌 생성자
function Account(accountNum, accountOwner, passwd, restMoney) {
	this.accountOwner = accountOwner;
	this.accountNum = accountNum;
	this.passwd = passwd;
	this.restMoney = restMoney;
}

// Prototype 객체에 메서드 생성

// 입금 메서드
Account.prototype.deposit = function(inputMoney) {
	if (inputMoney < 0) {
		console.log('0원 이상 입금할 수 있습니다.');
		return false;
	}
	return this.restMoney += inputMoney;
}

// 출금 메서드
Account.prototype.withdraw = function(outputMoney) {
	// 출금 금액이 잔액보다 크거나, 출금 금액이 마이너스 금액일 경우 처리
	if (this.restMoney < outputMoney || outputMoney < 0) {
		console.log('출금 금액을 확인해주세요.');
		return false;
	}
	return this.restMoney -= outputMoney;
}

// 비밀번호 검사 메서드
Account.prototype.checkPasswd = function(inputPasswd) {
	return this.passwd == inputPasswd;
}

// 계좌정보 출력 메서드
Account.prototype.toString = function() {
	return '입출금계좌\t' + this.accountNum + '\t' + this.accountOwner + '\t'
			+ this.restMoney;
}
