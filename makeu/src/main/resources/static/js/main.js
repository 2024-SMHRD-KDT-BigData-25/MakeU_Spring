 // 하트 버튼 클릭 이벤트 추가
 document.querySelectorAll(".heart-container").forEach((container) => {
    container.addEventListener("click", (e) => {
        const heart = e.currentTarget.querySelector(".heart-button");
        heart.classList.toggle("filled"); // 색상 채우기/비우기 토글
    });
});

function addLikes(idx) {
    console.log(idx);
    $.ajax({
        url:"main/likes"+idx,
        type:"get",

    })
}

$.ajax({
    url: "main/list", // 백엔드 컨트롤러 URL
    type: "get",
    success: function (result) {
        // 결과 데이터를 출력
        console.log(result);

        // main.html에 데이터를 추가
        let contentDiv = document.getElementById("content"); // 데이터를 삽입할 div 선택
        contentDiv.innerHTML = ""; // 기존 내용을 비움

        result.forEach(item => {
            let listItem = `<div class="item">
                                <h3>${item.title}</h3>
                                <p>${item.description}</p>
                            </div>`;
            contentDiv.innerHTML += listItem; // HTML 추가
        });
    },
    error: function () {
        alert("통신 실패!");
    },
});