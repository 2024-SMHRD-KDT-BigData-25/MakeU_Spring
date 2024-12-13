document.addEventListener('DOMContentLoaded', () => {
    // sessionStorage에서 데이터 가져오기
    const faceShape = sessionStorage.getItem('faceShape');
    const personalColor = sessionStorage.getItem('personalColor');

    // 얼굴형과 퍼스널 컬러 강조
    const faceShapeElements = document.querySelectorAll('.face-shape .shape img');
    const personalColorElements = document.querySelectorAll('.personal-color .color img');

    faceShapeElements.forEach((img) => {
        if (img.dataset.shape === faceShape) {
            img.style.opacity = "1";
            img.style.filter = "none";
        } else {
            img.style.opacity = "0.1";
            img.style.filter = "grayscale(100%)";
        }
    });

    personalColorElements.forEach((img) => {
        if (img.alt === personalColor) {
            img.style.opacity = "1";
            img.style.filter = "none";
        } else {
            img.style.opacity = "0.1";
            img.style.filter = "grayscale(100%)";
        }
    });
})

function saveMatchingResult() {
    // const creator = []
    // console.log("Creators from server:", creators);

    // // sessionStorage에 저장
    // sessionStorage.setItem("creators", JSON.stringify(creator));
    
    // const creators = JSON.parse(sessionStorage.getItem('creators')); // 세션 저장 시 JSON 형식으로 저장되었다고 가정
    // if (!creators || creators.length < 1) {
    //     alert("매칭 결과가 부족합니다.");
    //     return;
    // }

    // const id = JSON.parse(sessionStorage.getItem('members'));
    
    // // 상위 3개의 데이터 추출
    // const topCreators = creators.slice(0, 3);

    // const matchingResult = {
    //     memid: id.memid || null,
    //     matched1: topCreators[0]?.creatoridx || null, // 첫 번째 크리에이터 ID
    //     matched2: topCreators[1]?.creatoridx || null, // 두 번째 크리에이터 ID
    //     matched3: topCreators[2]?.creatoridx || null  // 세 번째 크리에이터 ID
    // };

    $.ajax({
        url: 'save', // 요청을 보낼 URL
        type: "POST", // HTTP 메서드
        success: function (result) {
            if (result === "login_error") { // 서버에서 로그인 오류를 반환
                alert("로그인을 해주세요!");
            } else if (result === "save_error") { // 서버에서 저장 오류를 반환
                alert("저장 실패!");
            } else if (result == "save_success"){
                alert("저장 성공!");
            }
        },
        error: function (xhr, status, error) {
            console.error("AJAX 요청 실패!");
            console.error("Status:", status);
            console.error("Error:", error);
            console.error("Response Text:", xhr.responseText);
            alert("오류가 발생했습니다. 다시 시도해주세요.");
        },
    });
    

    // fetch('/save', {
    //     method: 'POST',
    //     headers: { 'Content-Type': 'application/json' },
    //     body: JSON.stringify(matchingResult)
    // })
    // .then((response) => {
    //     if (response.status === 401) { // 401: Unauthorized
    //         alert("로그인을 해주세요!");
    //         throw new Error("로그인되지 않음");
    //     }
    //     if (!response.ok) {
    //         throw new Error('저장 실패!');
    //     }
    //     return response.text(); // 성공 메시지 반환
    // })
    // .then((message) => {
    //     alert(message);
    // })
    // .catch((error) => {
    //     console.error("저장 중 오류:", error);
    //     if (error.message !== "로그인되지 않음") {
    //         alert("저장에 실패했습니다. 다시 시도해주세요.");
    //     }
    // });
}