var cartDiv = document.getElementById('cartDiv');
var cart_reservation_div = document.getElementById("cart_reservation_div");
cartDiv.style.display = "none"
// console.log('trEST')
//const cartDivBtn = document.getElementById('cartDivBtn');

var pos = false;

function printMdal() {
    if (pos) {
        $('#cartDivBtn').removeClass('clicked');
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
    $("#cart_list_table").show();
    $("#cart_reservation_div").hide();
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
                var addHTML = ` <td>
                                    <div class="cart-list">
                                        <input id="selected_shop_${c.shopidx}" class="cart_select" type="radio" name="gender" value="샵이름">
                                    </div>
                                </td>
                                <td id="shop_info_img" class="shop_info_img" rowspan="3">
                                    <div class="shops_img_div">
                                        <img src="makeup/${c.shopname}1.jpg" alt="메이크업 사진">
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

function closeShopReserv() {
    cart_reservation_div.style.display = "none";
}

// 예약 버튼 클릭 시 이벤트 설정
$("#cart_reserv_btn").on("click", function () {
    var selected_toggled_id;
    document.querySelectorAll(".cart_select").forEach(item => {
        if (item.checked) {
            selected_toggled_id = item.id;
        }
    });

    if (selected_toggled_id == null) {
        alert("예약할 샵을 선택해주세요!")
    }

    var shop_cart_idx = getShopIdxFromId(selected_toggled_id);
    openReservDiv(shop_cart_idx);
    shop_info(shop_cart_idx);
});

function shop_info(idx) {
    // $("#cart_list_table").show();
    // $("#cart_reservation_div").hide();

    $.ajax({
        url: "shop/" + idx, // 서버 요청 URL
        type: "GET",
        success: function (shop) {
            if (!shop) {
                console.error("서버에서 받은 데이터가 없습니다.");
                return;
            }

            // 데이터 삽입
            // var tags = document.getElementById("shop_tag_div_" + idx).textContent;
            // document.getElementById("shop_detail_title").textContent = shop.shopname;
            // document.getElementById("shop_detail_location").textContent = shop.shoplocation;
            // document.getElementById("shop_detail_tags").textContent = tags;
            // document.getElementById("shop_detail_idx").textContent = idx;

            document.getElementById("reserv_title").textContent = shop.shopname;
            document.getElementById("reserv_idx").textContent = idx;

            $(document).on('click', '#cartDivBtn', function () {
                shop_info(shop.shopidx);
            });

        },
        error: function (xhr, status, error) {
            console.error("AJAX 요청 실패");
            console.error("상태:", status);
            console.error("오류:", error);
            console.error("응답 텍스트:", xhr.responseText);
        }
    });
}

function closeShopReserv() {
    cart_reservation_div.style.display = "none";
}

function openReservDiv(idx) {
    $("#cart_reservation_div").show();
}

function closeShopReserv() {
    cart_reservation_div.style.display = "none";
}

var today = new Date();
function buildCalendar() {
    var row = null
    var cnt = 0;
    var today_date = new Date().getDate();
    var today_month = new Date().getMonth();
    var calendarTable = document.getElementById("calendar");
    var calendarTableTitle = document.getElementById("calendarTitle");
    calendarTableTitle.innerHTML = today.getFullYear() + "년" + (today.getMonth() + 1) + "월";
    var firstDate = new Date(today.getFullYear(), today.getMonth(), 1);
    var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);
    while (calendarTable.rows.length > 2) {
        calendarTable.deleteRow(calendarTable.rows.length - 1);
    }

    row = calendarTable.insertRow();
    for (i = 0; i < firstDate.getDay(); i++) {
        cell = row.insertCell();
        cnt += 1;
    }

    row = calendarTable.insertRow();

    for (i = 1; i <= lastDate.getDate(); i++) {
        cell = row.insertCell();
        cnt += 1;

        cell.setAttribute('id', i);
        cell.innerHTML = i;
        cell.align = "center";

        cell.onclick = function (event) {
            // 이전 선택된 셀의 스타일 초기화
            const selectedCells = document.querySelectorAll('.date_selected');
            selectedCells.forEach(cell => {
                cell.classList.remove('date_selected');
                cell.style.backgroundColor = ''; // 초기화
            });



            // 현재 날짜 정보 가져오기
            const clickedYear = today.getFullYear();
            const clickedMonth = (1 + today.getMonth()).toString().padStart(2, '0'); // 2자리 변환
            const clickedDate = this.getAttribute('id').padStart(2, '0'); // 2자리 변환

            // 날짜 포맷
            const clickedYMD = `${clickedYear}-${clickedMonth}-${clickedDate}`;

            // 셀 강조 표시
            event.target.classList.add('date_selected');
            event.target.style.backgroundColor = "#6666";

            // 선택된 날짜 표시
            document.getElementById("date").textContent = clickedYMD;
        }

        if (cnt % 7 == 1) {
            cell.innerHTML = i;
        }

        if (cnt % 7 == 0) {
            cell.innerHTML = i;
            row = calendar.insertRow();
        }


        if (i == today_date && today.getMonth() == today_month) {
            cell.innerHTML += "<br><span style='font-size:0.8em;' id='today_span'>오늘</span>";
            cell.style.color = "#E01013";
            cell.style.border = "#E01013 2px solid";
            cell.style.borderRadius = "10px";

        } else {
            cell.innerHTML += "<br><span style='visibility:hidden;font-size:0.8em;' id='today_span'>오늘</span>";
        }
    }

    if (cnt % 7 != 0) {
        for (i = 0; i < 7 - (cnt % 7); i++) {
            cell = row.insertCell();
        }
    }
}

function prevCalendar() {
    today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
    buildCalendar();
}

function nextCalendar() {
    today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
    buildCalendar();
}
buildCalendar();

function makeReservation() {
    var selected_date = document.getElementById("c_date").textContent;
    var selected_time = document.getElementById("c_time").textContent;
    var selected_service = document.querySelector(".service_checkbox.checked + .service_name div").textContent;
    var requirement = document.getElementById('c_requirement').value;
    var selected_information = document.getElementById("c_information").textContent;

    if (selected_date == "") {
        alert("날짜를 선택해주세요!");
        return;
    }
    else {
        // 날짜 확인 로직
    }
    if (selected_time == "") {
        alert("시간을 선택해주세요!");
        return;
    }
    else {
        // 예약 가능한 시간인지 확인
    }
    if (selected_service == "") {
        alert("서비스를 선택해주세요!");
        return;
    }
    if (selected_information == "") {
        alert("정보 전송 여부를 선택해주세요!");
        return;
    }

    // const shopname = $("#reserv_detail_title").text;

    console.log("selected_date " + selected_date);
    console.log("selected_time " + selected_time);
    console.log("selected_service " + selected_service);
    console.log("requirement " + requirement);
    console.log("selected_information " + selected_information);
    // console.log(shopname)
    $.ajax({
        url: "reservation", // 쿼리 파라미터로 idx 전달
        type: "post",
        data: {
            shopidx: $("#reserv_idx").text(),
            reservationdatetime: selected_date + " " + selected_time,
            servicetype: selected_service,
            requirement: requirement
        },
        success: function () {
            console.log("예약 완료");
        },
        error: function () {
            alert("예약 불가!");
        }
    });
}

// 지도 크기 조정 함수
function toggleSectionCenter(isOpen) {
    var mapElement = document.getElementById('map');
    var sectionCenter = document.querySelector('.section-center');
    var sectionLeft = document.querySelector('.section-left');

    if (isOpen) {
        mapElement.style.width = '30vw'; // 지도 크기 변경
        sectionLeft.style.width = '30vw';
        sectionLeft.style.marginRight = '25%'; // section-left 옆에 공간 확보
        sectionCenter.style.display = 'block'; // 센터 열기
        sectionCenter.style.position = 'absolute';
        sectionCenter.style.left = '38vw'; // section-left 옆에 위치
    } else {
        mapElement.style.width = '70vw'; // 원래 크기 복구
        sectionLeft.style.width = '38vw';
        sectionLeft.style.marginRight = '0';
        sectionCenter.style.display = 'none'; // 센터 닫기
        sectionLeft.style.width = '38vw'; // 원래 크기 유지
    }

    map.relayout(); // 지도 크기 변화 반영
}

function getShopIdxFromId(idx) {
    // "selected_shop_" 기준으로 분리하고, 두 번째 부분(숫자)을 반환
    const carts = idx.split("_");  // 예: ["selected", "shop", "5"]
    return carts[2];  // 배열의 2번째 요소가 shopidx
}

document.querySelectorAll(".time_table div").forEach(timeSlot => {
    timeSlot.addEventListener("click", function () {
        // 모든 time_table div의 선택 스타일 초기화
        document.querySelectorAll(".time_table div").forEach(slot => {
            slot.classList.remove("time_selected");
            slot.style.backgroundColor = ""; // 기본 배경색으로 초기화
            slot.style.color = ""; // 기본 글자색으로 초기화
            slot.style.border = "";
        });

        // 클릭된 요소에 선택 스타일 추가
        this.classList.add("time_selected");
        this.style.backgroundColor = "#e01013";
        this.style.color = "white";
        this.style.border = "none";

        document.getElementById("time").textContent = timeSlot.textContent;
    });
});

document.querySelectorAll(".service_checkbox").forEach(checkbox => {
    checkbox.addEventListener("click", function () {
        // 이미 선택된 체크박스가 있다면, 모두 해제
        document.querySelectorAll(".service_checkbox").forEach(item => {
            item.classList.remove("checked");
        });

        // 현재 클릭된 체크박스에 "checked" 클래스를 추가
        this.classList.add("checked");
        document.getElementById("service").textContent = this.id;
    });
});

document.querySelectorAll(".info_checkbox").forEach(checkbox => {
    checkbox.addEventListener("click", function () {
        // 이미 선택된 체크박스가 있다면, 모두 해제
        document.querySelectorAll(".info_checkbox").forEach(item => {
            item.classList.remove("checked");
        });

        // 현재 클릭된 체크박스에 "checked" 클래스를 추가
        this.classList.add("checked");
        document.getElementById("information").textContent = this.id;
    });
});


document.querySelectorAll(".shop_info_table table").forEach(table => {
    table.addEventListener("click", function () {
        // 이미 선택된 체크박스가 있다면, 모두 해제
        document.querySelectorAll(".shop_info_table table").forEach(item => {
            item.classList.remove("selected_table");
        });

        // 현재 클릭된 체크박스에 "checked" 클래스를 추가
        this.classList.add("selected_table");
    });
});

