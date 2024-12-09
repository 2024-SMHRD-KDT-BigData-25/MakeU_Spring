var shop_info_div = document.getElementById("shop_detail_div");
var shop_reservation_div = document.getElementById("shop_reservation_div");
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

function shop_info(idx) {
    $("#shop_detail_div").show();
    // jQuery 방식
    $("#reserv_btn").on("click", function () {
        openReservDiv(idx);
    });
    $.ajax({
        url: "shop/" + idx, // 쿼리 파라미터로 idx 전달
        type: "GET",

        success: function (shop) {
            var tags = document.getElementById("shop_tag_div_" + idx).textContent;
            var stitle = document.getElementById("shop_detail_title");
            var sloc = document.getElementById("shop_detail_location");
            var stag = document.getElementById("shop_detail_tags");

            getPortpolios(idx);

            stitle.textContent = shop.shopname;
            sloc.textContent = shop.shoplocation;
            stag.textContent = tags;

        },
        error: function (xhr, status, error) {
            console.error("AJAX 실패!(shop_info)");
            console.error("Status:", status);
            console.error("Error:", error);
            console.error("Response Text:", xhr.responseText);
        },
    });
}

function closeShopDetail() {
    shop_info_div.style.display = "none";
}
function closeShopReserv() {
    shop_reservation_div.style.display = "none";
}

function openReservDiv(idx) {
    console.log(idx);
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
        this.style.backgroundColor="#e01013";
        this.style.color="white";
        this.style.border="none";
        
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

function makeReservation() {
    var selected_date = document.getElementById("date").textContent;
    var selected_time = document.getElementById("time").textContent;
    var selected_service = document.getElementById("service").textContent;
    var requirement = document.getElementById('requirement').value;
    var selected_information = document.getElementById("information").textContent;

    if (selected_date =="") {
        alert("날짜를 선택해주세요!");
        return;
    }
    else {
        // 날짜 확인 로직
    }
    if (selected_time =="") {
        alert("시간을 선택해주세요!");
        return;
    }
    else {
        // 예약 가능한 시간인지 확인
    }
    if (selected_service =="") {
        alert("서비스를 선택해주세요!");
        return;
    }
    if (selected_information =="") {
        alert("정보 전송 여부를 선택해주세요!");
        return;
    }
    
    console.log("selected_date " + selected_date);
    console.log("selected_time " + selected_time);
    console.log("selected_service " + selected_service);
    console.log("requirement " + requirement);
    console.log("selected_information " + selected_information);
}