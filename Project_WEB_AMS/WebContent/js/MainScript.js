/**
 * 메인 스크립트 작성
 * author 이철우
 */

window.onload = function() {
	init();
	eventRegist();
}

function init() {
	// 계좌 관리를 위한 AccountManager 생성
 	accountList = new AccountManager();
 	
 	// 테스트 테이터 주입
 	testData();
	
 	// 전체계좌 출력을 위해 초기화 부분에 선언 
 	blockInput();
}
   
// 이벤트 등록 메서드
function eventRegist() {
	// 클릭 이벤트를 위한 변수 선언 
 	var create = document.getElementById('create_account');
 	var typeList = document.getElementById('account_type_list');
 	var showAll = document.getElementById('list_all');
	var findOwner = document.getElementById('name_find');
	var findNumber = document.getElementById('number_find');
	var removeAccount = document.getElementById('account_delete');
 		
	// 클릭 이벤트 등록
	create.onclick = add;
	showAll.onclick = listAll;
	findOwner.onclick = searchName;
	findNumber.onclick = searchNum;
	removeAccount.onclick = remove;
	typeList.onclick = blockInput;
}

// 테스트 데이터 주입 메서드
function testData() {
 	var account1 = new Account('1111-1111-1111', '이철우', 1111, 10000);
	var account2 = new Account('2222-1111-1111', '이철우', 1111, 30000);
	var account3 = new Account('3333-1111-1111', '박철우', 1111, 40000);
	var account4 = new Account('4444-1111-1111', '김철우', 1111, 50000);
	var account5 = new MinusAccount('5555-1111-1111', '김철우', 1111, 20000, 50000);
	var account6 = new MinusAccount('6666-1111-1111', '김철우', 1111, 0, 50000);

	accountList.add(account1);
	accountList.add(account2);
	accountList.add(account3);
	accountList.add(account4);
	accountList.add(account5);
	accountList.add(account6);
}

   	
// -------------------------- 동작 메서드 구현 시작부 --------------------------


/**
 * 계좌 등록 메서드
 * 
 * 호출과 동시에 입력값들을 validation 메서드에 전달해 유효성 검사를 진행합니다.
 * 이 후, 계좌 종류에 맞게 계좌생성을 진행하며 계좌생성 성공 시 true를 반환합니다.
 * 
 */ 
function add() {
	if(validation()) {
		
		var accountType = document.querySelector('input[name=account_type]:checked').value;
		var accountNumber = document.getElementById('account_number');
		var accountName = document.getElementById('account_name').value;
 		var passwd = document.getElementById('passwd').value;
 		var inputMoney = document.getElementById('input_money').value;
 		// 계좌 생성 성공 여부 판단을 위한 flag
 		var flag = false;
 		
 		if(accountType == 'minus_account') {
			var borrowMoney = document.getElementById('borrow_money').value;
			flag = accountList.add(new MinusAccount(accountNumber.value, 
													accountName, 
													passwd, 
													inputMoney, 
													borrowMoney));	
 		} else {
	 		flag = accountList.add(new Account(accountNumber.value, 
	 										   accountName, 
	 										   passwd, 
	 										   inputMoney));
 		}
 		
 		// 중복 계좌일 경우, Account, MinusAccount의 add메서드에서 false를 반환합니다.
 		// 이 후, 성공 여부 메세지를 출력하기 위해 messageParsing 메서드를 호출합니다.
 		if(!flag) {
 			var errorInform = [{ id : accountNumber.id, value : "중복되는 계좌번호가 있습니다." } ];
 			messageParsing(errorInform);
 		} else {
 			var SuccessInform = [{ id : accountNumber.id, value : "계좌 생성 성공!" } ];
 			messageParsing(SuccessInform);
 		}
 		return flag;
	}
}


/**
* 전체 계좌 출력 메서드
* 
* 호출과 동시에 생성된 계좌가 있는지 확인합니다.
* 이 후, AMS화면의 계좌 종류 버튼에 따라 table을 생성합니다.
* 
*/ 
function listAll() {
	// 계좌 존재 여부 확인 
	if(accountList.listAll()) {
		var allList = accountList.listAll();
		var minusList = [];
		var depositList = [];
		// 체크된 라디오 버튼의 값을 받아오는 변수
		var accountType = document.querySelector('input[name=account_type]:checked').value;

		// 전체계좌 선택 시 모든 계좌를 table에 생성합니다.
		if(accountType == 'account_all') {
			return createTable(allList);
		// 다른계좌 선택 시 종류에 따라 배열에 담아 원하는 종류를 table에 생성합니다.  
		} else {
			// 계좌 종류별 계좌 담기 
			for ( var index in allList) {
				if(allList[index] instanceof MinusAccount) {
					minusList.push(allList[index]);
				} else {
					depositList.push(allList[index]);
				}
			}
			
			// 선택한 계좌 table에 생성하기
			if(accountType == 'deposit_account') {
				return createTable(depositList);
			} else {
				return createTable(minusList);
			}
		}
	}
	
	// 생성되어있는 계좌가 없을 경우 메세지파싱 메서드에 정보를 보냅니다.
	var accountNumber = document.getElementById('account_number');
	var errorInform = [ { id : accountNumber.id, value : "개설된 계좌가 없습니다." } ];
		messageParsing(errorInform);
		
	return false;
}


/**
* 예금주명 기준 계좌 검색 메서드
* 
* 호출과 동시에 입력된 예금주명에 대한 유효성 검사를 진행합니다.
* 이 후, 예금주명을 기준으로 계좌를 찾은 후, 성공 여부에 따라 table을 생성하거나 메세지파싱 메서드에 정보를 보냅니다.
* 
*/ 
function searchName() {
	var accountOwner = document.getElementById('account_name');
	
	if(validation(accountOwner)) {
		var ownerList = accountList.searchOwner(accountOwner.value);
		if(ownerList) { 
			return createTable(ownerList); 
		}
		
		var errorInform = [ { id : accountOwner.id, value : "예금주명과 일치하는 계좌가 없습니다." } ];
		messageParsing(errorInform);
		return false;
	}
}


/**
* 계좌번호 기준 계좌 검색 메서드
* 
* 호출과 동시에 입력된 계좌번호에 대한 유효성 검사를 진행합니다.
* 이 후, 계좌번호를 기준으로 계좌를 찾은 후, 성공 여부에 따라 table을 생성하거나 메세지파싱 메서드에 정보를 보냅니다.
* 
*/
function searchNum() {
	var accountNumber = document.getElementById('account_number');
	if(validation(accountNumber)) {
		var numberList = accountList.searchNumber(accountNumber.value);
		if(numberList) {
			return createTable(numberList);
		}
		
		var errorInform = [ {id : accountNumber.id, value : "계좌번호와 일치하는 계좌번호가 없습니다." } ];
		messageParsing(errorInform);
		return false;
	}
}


/**
* 계좌 삭제 메서드
* 
* 호출과 동시에 입력된 계좌번호에 대한 유효성 검사를 진행합니다.
* 이 후, 삭제를 진행하며, 성공 여부에 따라 메세지파싱 메서드에 정보를 보냅니다. 
* 
*/
function remove() {
	var accountNumber = document.getElementById('account_number');
	var flag = false;
	if(validation(accountNumber)) {
		if(!(flag = accountList.remove(accountNumber.value))){
			var errorInform = [ {id : accountNumber.id, value : "삭제 가능한 계좌가 없습니다." } ];
			messageParsing(errorInform);
		}
		return flag;
	}
}


/**
* 계좌종류 선택에 따른 input 박스 블락 메서드
* 
* 신규등록 버튼 클릭을 막기위해 init 메서드에 선언되어 있습니다. 
* 전체계좌, 입출금계좌, 마이너스계좌에 따라 input 박스에 효과를 주는 역할을 합니다.
* 
*/
function blockInput() {
	var accountType = document.querySelector('input[name=account_type]:checked').value;
	var inputBorrow = document.getElementById('borrow_money');
	var addButton = document.getElementById('create_account');
	
	// 계좌종류에 맞춰 계좌들을 출력합니다.
	listAll();
	
	// 마이너스계좌일 경우 대출금액 박스를 사용 가능하게 합니다.
	if(accountType == 'minus_account') {
		inputBorrow.disabled = false;
		inputBorrow.style.border = '1px solid #ccc';
		inputBorrow.value = 0;
		addButton.disabled = false;
	// 입출금계좌일 경우 대출금액 박스를 사용 불가능하게 합니다.
	} else if (accountType == 'deposit_account'){
		inputBorrow.disabled = true;
		inputBorrow.style.border = '2px solid red';
		inputBorrow.value = "기입 불가";
		addButton.disabled = false;
	// 전체계좌일 경우 신규등록 버튼을 사용 불가능하게합니다. 
	} else {
		inputBorrow.style.border = '1px solid #ccc';
		inputBorrow.value = 0;
		addButton.disabled = true;
	}
}

/**
* 테이블 생성 메서드
* 
* 계좌 리스트(account)를 매개변수로 받아 테이블을 생성합니다.
* 
*/
function createTable(account) {
	var table = document.getElementById('table_body');
	var accountType = '입출금계좌';
	var restMoney = 0;
	var borrowMoney = 0;
	
	// 기존의 생성되어있던 테이블 초기화 
	tableClear();
	
	// 입력된 계좌의 개수만큼 테이블 생성
	for (var i = 0; i < account.length; i++) {
		// insert할 row 선택
		var row = table.insertRow(i);
		
		// 계좌 종류에 따라 3가지 항목을 초기화합니다.
		if(account[i] instanceof MinusAccount) {
			accountType = '마이너스계좌';
			restMoney = account[i].getRestMoney();
			borrowMoney = account[i].borrowMoney;
		} else {
			accountType = '입출금계좌';
			restMoney = account[i].restMoney;
			borrowMoney = 0;
		}
		
		// 모든 항목을 row에 집어넣습니다.
		row.insertCell(0).innerHTML = accountType;
		row.insertCell(1).innerHTML = account[i].accountNum;
		row.insertCell(2).innerHTML = account[i].accountOwner;
		row.insertCell(3).innerHTML = restMoney;
		row.insertCell(4).innerHTML = borrowMoney;

	}
}


/**
* 유효성 검사 메서드
* 
* 오버로딩 기능을 구현하고자 arguments 객체를 이용했습니다.
* 모든 입력값을 확인해야하는 신규등록과 나머지 메서드들을 분리해 검사합니다.
* 입력될 값, 그리고 입력될 값에 해당하는 정규식을 배열에 미리 담아 사용합니다.
* 
*/
function validation() {
	// 에러 메세지 정보를 담을 배열
	var errorInform = [];
	
	// 입력될 값, 해당하는 정규식에 대한 배열
	var idList = ['account_number', 
		 		  'account_name', 
		 		  'passwd', 
		 		  'input_money', 
		 		  'borrow_money'];
	var regexList = [/^[1-9]\d{3}-\d{4}-\d{4}$/,
					 /^[가-힣]{2,5}$|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/,
					 /^[0-9]{4}$/,
					 /^[0-9]{0,9}$/,
					 /^[0-9]{0,9}$/];

	
	// 신규등록 유효성 검사
	// arguments == 0 : 신규등록 / ==1 : 나머지 메서드
	if (arguments.length == 0) {
		var accountType = document.querySelector('input[name=account_type]:checked').value;
		var listLength = null;
		
		// 신규등록 시, 대출금액을 고려해 입출금계좌와 마이너스계좌의 길이를 달리받아 유효성을 검사합니다.
		(accountType == 'deposit_account') ? listLength = idList.length -1 : listLength = idList.length;
			
		// 순회를 돌며 각 항목에 대해 유효성을 검사합니다.
 		for(var i=0; i < listLength; i++) {
 			var inputData = document.getElementById(idList[i]);
 			if(!regexList[i].test(inputData.value)) {
 				// 유효성 검사 실패시, 각 항목의 정보를 에러메세지 정보에 추가합니다.
 				errorInform.push({ id : inputData.id, value : null });
 			}
 		}
 		
 		// 유효성검사를 모두 통과했으면 length가 0이므로 true를 반환하고, 아닐경우 메세지파싱 메서드에 정보를 보냅니다.
 		if(errorInform.length) {
			messageParsing(errorInform);
			return false;
		}
 		return true;
 	// 조회, 삭제, 검색에 대한 유효성 검사
	} else {
		// arguments 객체를 이용해 매개변수로 받아온 정보를 이용합니다.
		var inputData = arguments[0];
		// indexof 메서드를 이용해 매개변수에 맞는 정규식의 index를 찾습니다.
		var index = idList.indexOf(inputData.id);
		// 이 후, 유효성검사 진행하며 불통과시 메세지파싱 메서드에 정보를 보냅니다.
		if(regexList[index].test(inputData.value)) { 
			return true; 
		} else {
			errorInform = [{ id : inputData.id, value : null }];
			// 유효성 검사 실패
			messageParsing(errorInform);
			return false;
		}
	}
}


/**
* 메세지 파싱 메서드
* 
* 메세지에 포함된 정보를 바탕으로 해당되는 메세지 문구를 분류합니다.
* 포함된 정보와 span 태그의 위치를 key, value 형태로 Object 객체에 담아 사용합니다.
* 유효성검사 메서드와 동일하게 신규등록과 나머지 메서드들을 분기해 처리했습니다.
* 
*/
function messageParsing(arg) {
	var selector = null;
	var spanId = null;
	var errorMessage = null;
	// 정보와 span 태그를 key, value로 넣은 Obejct 객체
	var spanList = {
					 'account_number' : 'number_error',
					 'account_name' : 'name_error',
					 'passwd' : 'passwd_error',
					 'input_money' : 'input_error',
					 'borrow_money' : 'borrow_error' 
	};
	
	// 에러메세지를 보내기 전, 테이블을 초기화합니다.
	tableClear();
	
	// 조회, 삭제, 검색 에러메세지 
	if (arg.length == 1) {
		// 입력된 정보의 id값을 받아 span태그의 위치를 찾습니다.
		for ( var index in spanList) {
			if(arg[0].id == index) {
				selector = document.getElementById(spanList[index]);
				break;
			}
		}
		// 유효성 검사와 중복, 계좌 존재 여부 검사에 따라 나눠 처리합니다.
		// 유효성 검사 : arg[0].value : null
		// 존재 여부 및 삭제 성공에 따른 메세지 출력
		if(arg[0].value != null) {
			errorMessage = arg[0].value;
			messagePrint(errorMessage, selector);
			// 유효성 검사에 실패한 경우	
		} else {
			if(spanId == 'name_error') {
				messagePrint("입력된 예금주명을 확인해주세요.", selector);
			} else {
				messagePrint("입력된 계좌번호를 확인해주세요.", selector);
			}
		}
	// 신규등록 에러메세지
	} else {
		// 에러가 있는 항목만큼 순회
		for ( var i in arg) {
			// span 태그 개수만큼 순회
			for ( var j in spanList) {
				// 에러 항목과 Object 객체의 key값 비
				if(arg[i].id == j) {
					selector = document.getElementById(spanList[j]);
					// span위치에 따라 분기해 메세지 출력
					switch(arg[i].id) {
						case 'account_number' :  messagePrint("입력된 계좌번호를 확인해주세요", selector);
												 break; 
						case 'account_name' :    messagePrint("입력된 예금주명을 확인해주세요", selector);
												 break;
						case 'passwd' : 		 messagePrint("입력된 비밀번호를 확인해주세요", selector);
						 						 break;
						case 'input_money' : 	 messagePrint("입력된 입금금액을 확인해주세요", selector);
												 break;
						case 'borrow_money' : 	 messagePrint("입력된 대출금액을 확인해주세요", selector);
												 break;
						default : break;
					}
				}
			}
		}
	}
}



/**
* 메세지 출력 메서드
* 
* setInterval, clearInterval을 이용해 2.5초간 에러메세지를 출력합니다.
* 
*/
function messagePrint(errorMessage, selector) {
	var count = 0;
	var fadeIn = setInterval( function() {
		selector.style.visibility = 'visible';
		selector.innerHTML = errorMessage;
		if(count >= 5) {
			selector.style.visibility = 'hidden';
			clearInterval(fadeIn);
			return ;
		}
		count++;
	}, 500);
}


/**
* 테이블 초기화 메서드
* 
* 테이블을 초기화합니다.
* 
*/
function tableClear() {
	var table = document.getElementById('table_body');
	table.innerHTML = '';
}