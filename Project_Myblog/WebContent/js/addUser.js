

document.getElementById("confirmIdButton").onclick = function() {
	var userId = document.getElementById('userId');
	var a = joinValidation(userId);
	if(a) {
		location.href = "/pUser/addUserAction.jsp?userId=" + userId.value;
	}
}

function confirmClear() {
	var inputIdTag = document.getElementById("userId");
	var requestButton = document.getElementById("sendUserInform");
	var confirmButton = document.getElementById("confirmIdButton");
	var subText = document.getElementById("addUserSubText");
	var idSpan = document.getElementById("idError");
	
	idSpan.style.color = '#2ed454';
	idSpan.innerText = '중복검사 성공!';
	idSpan.style.visibility = 'visible';
	inputIdTag.readOnly = true;
	requestButton.disabled = false; 
	confirmButton.style.visibility = 'hidden';
	subText.innerText = '가입 신청이 가능합니다.';
	subText.style.color = '#2ed454';
}

function confirmIdFalse() {
	var errorData = [{ id : 'userId', value : "이미 존재하는 아이디입니다." }];
	messageParsing(errorData);
}

function confirmEmailFalse() {
	var errorData = [{ id : 'email', value : "이미 존재하는 이메일입니다." }];
	messageParsing(errorData);
}