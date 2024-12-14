function printMdal() {
    if ($('#cartModal').hasClass('clicked')) {
        $('#cartModal').removeClass('clicked').hide(); // 클래스 제거 및 숨기기
    } else {
        $('#cartModal').addClass('clicked').show(); // 클래스 추가 및 표시

        $.ajax({
            url: "shop-cart",
            type: "GET",
            success: function (cart) {
                if (!cart) {
                    console.error("서버에서 받은 데이터가 없습니다.");
                    return;
                }


                var cartTable = document.getElementById('cart_list_table');
                cartTable.innerHTML = "";
                cart.forEach((c) => {
                    var addHTML = `<div class="cart_items_wrap">
                                        <div class="cart-list">
                                            <input id="selected_shop_${c.shopidx}" class="cart_select" type="radio" name="gender" value="샵이름">
                                        </div>
                                        <div id="shop_info_img" class="shop_info_img">
                                            <div class="cart_shops_img_div">
                                                <img src="makeup/${c.shopname}1.jpg" alt="메이크업 사진">
                                            </div>
                                        </div>
                                        <div class="cart_list">
                                            <span class="shop_name">${c.shopname}</span>
                                            <span class="shop_loca">${c.shoplocation}</span>
                                        </div>
                                         <div class="cart-delete" onclick="delete_cart(${c.shopidx})">
                                         &times;
                                         </div>
                                    </div>`;
                    cartTable.innerHTML += addHTML;
                })

            },
            error: function (xhr, status, error) {
                console.error("AJAX 요청 실패");
                console.error("상태:", status);
                console.error("오류:", error);
                console.error("응답 텍스트:", xhr.responseText);
            }

        });
    }
}

function delete_cart(idx) {
    if (confirm("장바구니에서 삭제하시겠습니까??")) {
        $.ajax({
            url: "cartDelete/"+idx,
            type: "POST",
            success: function (response) {
                alert("삭제되었습니다.");
                location.reload();
            },
            error: function (xhr, status, error) {
                alert("삭제에 실패했습니다: " + xhr.responseText);
            },
        });
    }
}

$("#cart_reserv_btn").on("click", function () {
    var selected_toggled_id;
    document.querySelectorAll(".cart_select").forEach(item => {
        if (item.checked) {
            selected_toggled_id = item.id;
        }
    });

    if (selected_toggled_id == null) {
        alert("예약할 샵을 선택해주세요!")
        return;
    }

    const select_shopidx = selected_toggled_id.split("_")[2];
    console.log(select_shopidx);
    if (confirm("예약 창으로 이동합니다")) {
        window.location.href = `shopmatching?shopidx=${select_shopidx}`;
    }
});

