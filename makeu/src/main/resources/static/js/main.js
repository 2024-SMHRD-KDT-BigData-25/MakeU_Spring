// 하트 버튼 클릭 이벤트 추가
document.querySelectorAll(".heart-container").forEach((container) => {
    container.addEventListener("click", (e) => {
        const heart = e.currentTarget.querySelector(".heart-button");
        heart.classList.toggle("filled"); // 색상 채우기/비우기 토글
    });
});

function addLikes(idx) {
    const memIdElement = document.getElementById("mem_id");

    if (memIdElement) {
        const mem_id = memIdElement.textContent;
        $.ajax({
            url: "likes/" + mem_id + "/" + idx, // 쿼리 파라미터로 idx 전달
            type: "GET",

            success: function (status) {
                const imageDiv = document.getElementById("heart_img_"+idx);
                imageDiv.style.opacity=status ? "1" : "0";
                
            },
            error: function (xhr, status, error) {
                console.error("AJAX 실패!");
                console.error("Status:", status);
                console.error("Error:", error);
                console.error("Response Text:", xhr.responseText);
            },
        });
    } else {
        console.log("mem_id가 존재하지 않음");  // mem_id가 존재하지 않으면 처리
    }
    
}

function hoverFunction(idx) {
    const tagSpan = document.getElementById("image_tags"+idx); 
    if (tagSpan.textContent !=  "") {
        return;
    }
    $.ajax({
        url: "tags/" + idx, // 쿼리 파라미터로 idx 전달
        type: "GET",

        success: function (tags) {
            tagSpan.textContent = tags;
        },
        error: function (xhr, status, error) {
            console.error("AJAX 실패!");
            console.error("Status:", status);
            console.error("Error:", error);
            console.error("Response Text:", xhr.responseText);
        },
    });
}

// 페이지 상단으로 이동하는 함수
function scrollToTop() {
    window.scrollTo({
        top: 0, // 상단으로 이동
        behavior: 'smooth' // 부드럽게 이동
    });
}