/**
* 유효성 검사 메서드
* 
* 오버로딩 기능을 구현하고자 arguments 객체를 이용했습니다.
* 모든 입력값을 확인해야하는 신규등록과 나머지 메서드들을 분리해 검사합니다.
* 입력될 값, 그리고 입력될 값에 해당하는 정규식을 배열에 미리 담아 사용합니다.
* 
*/

var joinUser = document.getElementById("sendUserInform");
joinUser.onclick = function() {
	joinValidation();
}

function joinValidation() {
	// 에러 메세지 정보를 담을 배열
	var errorInform = [];
	var inputData = null;
	var inputCPasswd = null;

	var idList = ['userId',
				  'userName',
				  'userPasswd',
				  'email'];
	console.log("들어온 arg : " + arguments.length);
	var regexList = [/^[a-zA-z][a-zA-z0-9]{5,9}$/,
					 /^[가-힣]{2,6}$/,
					 /^[a-zA-Z0-9]{6,10}$/,
					 /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/];
				
	if(arguments.length == 0) {
		for(var i = 1; i < idList.length; i++) {
			inputData = document.getElementById(idList[i]);
			if(!regexList[i].test(inputData.value)) {
				errorInform.push({ id : inputData.id, value : null});
			}
		}
		// 패스워드 유효성 검사
		inputData = document.getElementById('userPasswd');
		inputCPasswd = document.getElementById("confirmPasswd");
		if(inputData.value != inputCPasswd.value) {
			errorInform.push({ id : inputCPasswd.id, value : null});
		} 
	} else {
		if(arguments[0].value) {
			if(!regexList[0].test(arguments[0].value)) {
				errorInform.push({ id : arguments[0].id, value : null});
			}
		}
	}
	
	if(errorInform.length) {
		messageParsing(errorInform);
		return false;
	}
	
	if(arguments.length == 0) {
		var form = document.createElement('form');
		form.setAttribute("charset", "UTF-8");
		form.setAttribute("method", "Post"); // Get 또는 Post 입력
		form.setAttribute("action", "addUserAction.jsp");
		 
		for ( var i = 0; i < idList.length; i++) {
			
			var hiddenField = document.createElement("input");
			var tagData = document.getElementById(idList[i]);
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", idList[i]);
			hiddenField.setAttribute("value", tagData.value);
			form.appendChild(hiddenField);
		}
		document.body.appendChild(form);
	    form.submit(); 

//		location.href= "/pUser/addUserAction.jsp?email=" + inputEmail;
//		response.sendRedirect("/pUser/addUser.jsp?id=" + user.getId() + "&name=" + user.getName() 
//                + "&passwd=" + user.getPasswd()+ "&email=" + user.getEmail() + "&exist=" + exist);
	}
	return true;
}


function messageParsing(errorInform) {
	var selector = null;
	var spanId = null;
	var spanList = {
					 'userId' : 'idError',
					 'userName' : 'nameError',
					 'userPasswd' : 'passwdError',
					 'confirmPasswd' : 'cPasswdError',
					 'email' : 'emailError' 
				   };
	
	for(var error in errorInform) {
		for(var tag in spanList) {
			if(errorInform[error].id == tag) {
				selector = document.getElementById(spanList[tag]);
				switch(errorInform[error].id) {
				case 'userId' :  		 if(errorInform[error].value != null) {
											 messagePrint(errorInform[error].value, selector);
											 break;
										 }
										 messagePrint("입력하신 ID를 확인해주세요.", selector);
										 break; 
				case 'userName' :    	 messagePrint("입력하신 이름을 확인해주세요.", selector);
										 break;
				case 'userPasswd' : 	 messagePrint("입력하신 비밀번호를 확인해주세요.", selector);
				 						 break;
				case 'confirmPasswd' : 	 messagePrint("비밀번호가 일치하지 않습니다.", selector);
										 break;
				case 'email' :			 if(errorInform[error].value != null) {
											 messagePrint(errorInform[error].value, selector);
											 break;
										 } 	 		 
										 messagePrint("입력하신 이메일을 확인해주세요.", selector);
										 break;
				default : break;
				}
			}
		}
	}
}

function messagePrint(Message, selector) {
	var selColor = selector;
	selector.innerHTML = Message;
	selector.style.visibility = 'visible';
	
	var messageClose = setInterval( function() {
		selector.style.visibility = 'hidden';
		clearInterval(messageClose);
	},  4000);
}
