function validateForm() {
	// 작성된 값 가져오기 
	const mem_id = document.getElementById('mem_id').value.trim()
	const mem_pw = document.getElementById('mem_pw').value.trim()
	const pw_confirm = document.getElementById('pw_confirm').value.trim()
	const mem_name = document.getElementById('mem_name').value.trim()
	const mem_nickname = document.getElementById('mem_nickname').value.trim()


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

	return false;
}