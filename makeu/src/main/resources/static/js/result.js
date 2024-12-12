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
})