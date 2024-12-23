var shop_info_div = document.getElementById("shop_detail_div");
var shop_reservation_div = document.getElementById("shop_reservation_div");

// URL 파라미터에서 shopidx 추출
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

const shopidx = getQueryParam('shopidx');
const cartidx = getQueryParam('cartidx');

if (shopidx) {
    shop_info(shopidx);
    openReservDiv(shopidx); // shop_info 함수 호출
}


var center = new kakao.maps.LatLng(34.950705, 127.487627);
if (navigator.geolocation) {

    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function (position) {

        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도

        center = new kakao.maps.LatLng(lat, lon);
    });

}
else {

}

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: center, // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨 
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

function getPortpolios(idx) {
    var sport = document.getElementById("shop_detail_portpolio");
    $.ajax({
        url: "ports/" + idx, // 쿼리 파라미터로 idx 전달
        type: "GET",

        success: function (portpolios) {
            while (sport.firstChild) {
                sport.removeChild(sport.firstChild);
            }

            portpolios.forEach(src => {
                var div = document.createElement("div");
                var img = document.createElement("img");
                img.src = "makeup/" + src.portfolioimg;
                img.alt = "포트폴리오 이미지";
                img.style.width = "100%";
                div.className = "shop_port_grid"
                div.appendChild(img);
                sport.appendChild(div);
            });

        },
        error: function (xhr, status, error) {
            console.error("AJAX 실패!");
            console.error("Status:", status);
            console.error("Error:", error);
            console.error("Response Text:", xhr.responseText);
        },
    });
}

// 마커를 저장할 배열 선언
var markers = [];

function shop_info(idx) {
    $("#shop_detail_div").show();
    $("#shop_reservation_div").hide();

    // 예약 버튼 클릭 시 이벤트 설정
    $("#reserv_btn").on("click", function () {
        openReservDiv(idx);
    });

    $.ajax({
        url: "shop/" + idx, // 서버 요청 URL
        type: "GET",
        success: function (shop) {
            if (!shop) {
                console.error("서버에서 받은 데이터가 없습니다.");
                return;
            }

            // 데이터 삽입
            var tags = document.getElementById("shop_tag_div_" + idx).textContent;
            document.getElementById("shop_detail_title").textContent = shop.shopname;
            document.getElementById("shop_detail_location").textContent = shop.shoplocation;
            document.getElementById("shop_detail_tags").textContent = tags;


            document.getElementById("reserv_title").textContent = shop.shopname;
            document.getElementById("reserv_idx").textContent = idx;
            document.getElementById("shop_detail_idx").textContent = idx;

            getPortpolios(idx);

            // 지도 위치 이동
            var geocoder = new kakao.maps.services.Geocoder();
            geocoder.addressSearch(shop.shoplocation, function (result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                    // 이전 마커 제거
                    removeMarkers();

                    // 새로운 마커 생성
                    var marker = new kakao.maps.Marker({
                        map: map,
                        position: coords
                    });

                    // 마커를 배열에 저장
                    markers.push(marker);

                    var OFFSET = 0.0035; // 경도를 0.001 줄여 약간 왼쪽으로 이동
                    var newCoords = new kakao.maps.LatLng(coords.getLat(), coords.getLng() - OFFSET);
                    // 지도의 중심 이동
                    map.setCenter(newCoords);
                } else {
                    console.error("주소 검색 실패! 상태 코드:", status);
                }
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

// 기존 마커를 모두 제거하는 함수
function removeMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null); // 지도에서 마커 제거
    }
    markers = []; // 배열 초기화
}

function closeShopDetail() {
    shop_info_div.style.display = "none";
    document.querySelectorAll(".shop_info_table table").forEach(item => {
        item.classList.remove("selected_table");
    });
}
function closeShopReserv() {
    shop_reservation_div.style.display = "none";
}

function openReservDiv(idx) {
    $("#shop_reservation_div").show();

    $(".date_selected").removeClass("date_selected").css({
        backgroundColor: "",
        color: "",
        border: ""
    });

    $(".time_table div.time_selected").removeClass("time_selected").css({
        backgroundColor: "",
        color: "",
        border: ""
    });
    $(".service_checkbox.checked").removeClass("checked");
    $(".info_checkbox.checked").removeClass("checked");
    $(".shop_info_table table.selected_table").removeClass("selected_table");
    
    $("#date, #time, #service, #information").text("");
    
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
        const serviceidx = this.id.split("_")[1];
        document.getElementById("service").textContent = $(`#service_name_${serviceidx}`).text();
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

function makeReservation() {
    var selected_date = document.getElementById("date").textContent;
    var selected_time = document.getElementById("time").textContent;
    var selected_service = document.getElementById("service").textContent;
    var requirement = document.getElementById('requirement').value;
    var selected_information = document.getElementById("information").textContent;

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
            requirement: requirement,
            cartidx : cartidx
        },
        success: function (memid) {
            alert("예약 완료");
            window.location.href = "mypage?memid=" + memid;
        },
        error: function () {
            alert("예약 불가!");
        }
    });
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
            alert(xhr.responseText);
        }
    });
});