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

    // 결과 가져오기
    function fetchCreators(faceShape, personalColor) {
        fetch('http://localhost:8089/makeu/result', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ faceShape, personalColor }),
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then((data) => {
                console.log("받은 크리에이터 데이터:", data);

                // 화면에 데이터 표시
                const resultContainer = document.getElementById('result-container');
                resultContainer.innerHTML = ""; // 기존 결과 초기화
                data.forEach((creator) => {
                    const creatorElement = document.createElement('div');
                    creatorElement.className = 'creator';
                    creatorElement.innerHTML = `
                                                <div class="youtuber_img">
                                                    <img src="${creator.creatorlink}" alt="${creator.creatorname}">
                                                </div>
                                                <h4>${creator.creatorname}</h4>
                                                <p>${creator.creatorGender || "정보 없음"}</p>
                                                <p>${creator.creatorDesc || "설명 없음"}</p>
                                                <a href="${creator.creatorlink}" target="_blank">크리에이터 링크</a>`;
                    resultContainer.appendChild(creatorElement);
                });
            })
            .catch((error) => {
                console.error("크리에이터 데이터 가져오기 오류:", error);
                alert("데이터를 불러오는 중 오류가 발생했습니다.");
            });
    }
})