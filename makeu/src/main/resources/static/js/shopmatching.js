var shop_info_div = document.getElementById("shop_detail_div");
var center = new kakao.maps.LatLng(34.950705, 127.487627); 
if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        
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
        url: "ports/"+idx, // 쿼리 파라미터로 idx 전달
        type: "GET",

        success: function (portpolios) {
            while (sport.firstChild) {
                sport.removeChild(sport.firstChild);
            }

            console.log(portpolios.size);
            portpolios.forEach(src => {
                var div = document.createElement("div");
                var img = document.createElement("img");
                img.src = "makeup/"+src.portfolioimg;
                img.alt = "포트폴리오 이미지";
                img.style.width = "100%";
                div.className="shop_port_grid"
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
    shop_info_div.style.display ="none";
}