function validateForm() {
	// 작성된 값 가져오기 
	const mem_id = document.getElementById('memId').value.trim();
	const mem_pw = document.getElementById('memPw').value.trim();
	const pw_confirm = document.getElementById('pwConfirm').value.trim();
	const mem_name = document.getElementById('memName').value.trim();
	const mem_nickname = document.getElementById('memNickname').value.trim();
	if (mem_id === "") {
		alert("아이디를 입력해주세요")
		return false;
	}

	if (mem_pw === "") {
		alert("비밀번호를 입력해주세요")
		return false;
	}

	if (pw_confirm === "") {
		alert("비밀번호 확인을 해주세요")
		return false;
	}

	if (mem_name === "") {
		alert("이름을 입력해주세요")
		return false;
	}

	if (mem_nickname === "") {
		alert("닉네임을 입력해주세요")
		return false;
	}

	return true;
}

const mem_pw = document.getElementById('memPw');
const pw_confirm = document.getElementById('pwConfirm');
const errorMessage = document.getElementById('errorMessage');

mem_pw.addEventListener('input', function () {
	if (mem_pw.value !== pw_confirm.value) {
		errorMessage.style.display = 'block';
		errorMessage.style.textAlign = 'left';
		errorMessage.style.color = 'red';
		errorMessage.textContent = '비밀번호를 확인해주세요!';
	} else {
		errorMessage.style.color = 'green';
		errorMessage.textContent = '비밀번호가 일치합니다!';
	}
});

pw_confirm.addEventListener('input', function () {
	if (mem_pw.value !== pw_confirm.value) {
		errorMessage.style.display = 'block';
		errorMessage.style.textAlign = 'left';
		errorMessage.style.color = 'red';
		errorMessage.textContent = '비밀번호를 확인해주세요!';
	} else {
		errorMessage.style.color = 'green';
		errorMessage.textContent = '비밀번호가 일치합니다!';
	}
});