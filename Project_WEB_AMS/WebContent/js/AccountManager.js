/**
 * 
 * JavaScript를 이용한 AccountManager 클래스 구현 
 * 반환 계좌가 필요한 메서드를 제외하고 true/false를 반환합니다.
 * author 이철우
 * 
 */

// 생성자
function AccountManager() {
	this.accounts = [];
}

// 계좌 추가 메서드
AccountManager.prototype.add = function(account) {
	// accounts 배열을 순회하며 계좌번호 중복 확인
	for ( var key in this.accounts) {
		if (this.accounts[key].accountNum == account.accountNum) {
			return false;
		}
	}
	// 중복이 없을 시 계좌 추가
	this.accounts.push(account);
	return true;
}

// Prototype 객체에 메서드 생성

// 전체 계좌 목록 출력 메서드
AccountManager.prototype.listAll = function() {
	if (this.accounts.length) {
		return this.accounts;
	} else {
		return false;
	}
}

// 예금주명 기준 계좌 검색 메서드
AccountManager.prototype.searchOwner = function(accountOwner) {
	var ownerAccounts = [];
	for ( var key in this.accounts) {
		if (this.accounts[key].accountOwner == accountOwner) {
			ownerAccounts.push(this.accounts[key]);
		}
	}
	if (ownerAccounts.length) {
		return ownerAccounts;
	}
	return false;
}

// 계좌번호 기준 계좌 검색 메서드
AccountManager.prototype.searchNumber = function(accountNumber) {
	var numberAccount = [];
	for ( var key in this.accounts) {
		if (this.accounts[key].accountNum == accountNumber) {
			numberAccount.push(this.accounts[key]);
			return numberAccount;
		}
	}
	return false;
}

// 계좌 삭제 메서드
AccountManager.prototype.remove = function(accountNumber) {
	for ( var key in this.accounts) {
		if (this.accounts[key].accountNum == accountNumber) {
			// delete 메서드는 배열크기까지 삭제되지 않기에 splice 메서드 사용
			this.accounts.splice(key, 1);
			return true;
		}
	}
	return false;
}
