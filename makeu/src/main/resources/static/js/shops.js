var cartDiv = document.getElementById('cartDiv');
cartDiv.style.display = "none"
// console.log('trEST')
//const cartDivBtn = document.getElementById('cartDivBtn');

var pos = false;

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
$(document).on('click', '#cartDivBtn', function () {
    
    if ($(this).hasClass('clicked')) {
        return;  // 이미 클릭된 상태라면 실행하지 않음
    }
    $(this).addClass('clicked');  // 클릭된 상태로 표시

    $.ajax({
        url: "shop-cart",
        type: "GET",
        success: function (cart) {
            if (!cart) {
                console.error("서버에서 받은 데이터가 없습니다.");
                return;
            }

            // 데이터 삽입
            // document.getElementById("shop_idx").textContent = cart.shopidx;
            // document.getElementById("shop_name").textContent = cart.shopname;
            // document.getElementById("shop_loca").textContent = cart.shoplocation;
            var cartTable = document.getElementById('cart_list_table');
            cart.forEach((c) => {
                var addHTML = `<td>
                                    <div class="cart-list">
                                        <input id="selected_shop_${c.shopidx}" class="cart_select" type="radio" name="gender" value="샵이름">
                                    </div>
                                </td>
                                <td>
                                    <div class="cart-list">
                                        <span class="shop_name">${c.shopname}</span>
                                        <span class="shop_loca">${c.shoplocation}</span>
                                    </div>
                                </td>`;
                row = cartTable.insertRow();
                row.innerHTML = addHTML;
                            })
            

        },
        error: function (xhr, status, error) {
            console.error("AJAX 요청 실패");
            console.error("상태:", status);
            console.error("오류:", error);
            console.error("응답 텍스트:", xhr.responseText);
        }

    });
});

// function shop_reserv() {
//     // cart-container에 장바구니 내역 표시하기
//     const cartContainer = document.getElementsByClassName('cart-list');

//     // forEach로 장바구니 항목 순회
//     cartItems.forEach(cart => {
//         // 각 항목의 HTML 콘텐츠 생성
//         const cartItemDiv = document.createElement('div');
//         cartItemDiv.classList.add('cart-item');

//         // 항목 내용 추가
//         cartItemDiv.innerHTML = `
//             <p>Product: ${cart.shopname}</p>
//             <p>Price: $${cart.shoplocation}</p>
//         ;`

//         // 생성한 항목을 cart-container에 추가
//         cartContainer.appendChild(cartItemDiv);
//     });
// }

// 예약하기 버튼 클릭 시 해당 상품 예약 페이지로 이동
function reserveShop(productId) {
    window.location.href = 'shop/' + shopIdx;  // 예약 페이지로 리다이렉트
}