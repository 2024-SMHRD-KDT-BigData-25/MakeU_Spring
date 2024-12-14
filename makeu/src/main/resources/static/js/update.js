function validateForm() {
	const mem_id = document.getElementById('memid').value.trim()
	const mem_pw = document.getElementById('mempw').value.trim()
	const pw_confirm = document.getElementById('pwconfirm').value.trim()
	const mem_name = document.getElementById('memname').value.trim()
	const mem_nickname = document.getElementById('memnickname').value.trim()
	// 작성된 값 가져오기 
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

function deleteMember() {
	if (confirm("정말 탈퇴하시겠습니까?")) {
        window.location.href = 'quit';
    }
}