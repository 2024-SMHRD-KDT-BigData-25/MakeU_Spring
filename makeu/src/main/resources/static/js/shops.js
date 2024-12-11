const cartDiv = document.getElementById('cartDiv');
cartDiv.style.display = "none"
// console.log('trEST')
//const cartDivBtn = document.getElementById('cartDivBtn');

let pos = false;

function printMdal() {
    if (pos) {
        pos = false;
        cartDiv.style.display = "none"
    }
    else {
        pos = true;
        cartDiv.style.display = "block"
    }
}


// 장바구니 담기 버튼 클릭 시
$(document).on('click', '.add-to-cart', function () {
    const shopIdx = $("#shop_detail_idx").text();

    // AJAX 요청 보내기
    $.ajax({
        url: 'shops',  // 서버의 API 엔드포인트
        type: 'POST',
        data: {
            shopIdx: shopIdx
        },
        success: function () {
            alert('장바구니에 담겼습니다!');
        },
        error: function (xhr, status, error) {
            alert('장바구니에 담기 실패');
        }
    });
});



// // 모달 관련 요소 선택
// const modalContainer = document.getElementById('modalContainer');
// const closeButton = document.getElementById('closeButton');

// // SHOPS 버튼 클릭 시 모달 열기
// function openCartModal() {
//   // 모달 HTML을 동적으로 로드
//   fetch('cart-modal.html')
//     .then(response => response.text()) // HTML을 텍스트로 가져오기
//     .then(html => {
//       modalContainer.innerHTML = html; // 로드된 HTML을 modalContainer에 삽입

//       // 모달을 보이게 하기
//       const modal = document.getElementById('cartModal');
//       modal.style.display = 'block';

//       // 모달 닫기 버튼 클릭 시 모달 닫기
//       const closeButton = document.getElementById('closeButton');
//       closeButton.addEventListener('click', () => {
//         modal.style.display = 'none';
//         modalContainer.innerHTML = '';  // 모달 콘텐츠 초기화
//       });

//       // 모달 외부 클릭 시 모달 닫기
//       window.addEventListener('click', (event) => {
//         if (event.target === modal) {
//           modal.style.display = 'none';
//           modalContainer.innerHTML = '';  // 모달 콘텐츠 초기화
//         }
//       });
//     })
//     .catch(err => console.log("모달을 로드하는 데 실패했습니다:", err));
// }
