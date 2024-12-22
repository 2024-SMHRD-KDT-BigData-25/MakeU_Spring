# 🌸 **MAKE YOU: 딥러닝 기반 뷰티 어드바이저**

> **"Find Your Own Beauty with MAKE YOU"**  
MAKE YOU는 딥러닝 기술을 활용해 퍼스널 컬러와 얼굴형을 분석하고, 개인화된 뷰티 솔루션과 메이크업 스타일을 추천하는 혁신적인 뷰티 플랫폼입니다.
---

## 🌟 **서비스 소개**  
![서비스 소개](https://github.com/user-attachments/assets/3cf61a10-c10c-47c2-a892-9193e2247bf3)  

---

## 🎯 **기획 배경**  
사람들은 외적인 아름다움을 중시하며, 자신만의 스타일을 찾아가고자 합니다. 특히 MZ 세대를 중심으로 퍼스널 컬러와 체형 분석 서비스에 대한 수요가 증가하고 있습니다.  

> **MAKE YOU**는 IT 기술을 활용하여 무료로 퍼스널 진단 서비스를 제공함으로써 소비자의 경제적 부담을 줄이고, 개인화된 뷰티 솔루션을 제안합니다.

![기획 배경](https://github.com/user-attachments/assets/74c7a559-9a4d-4db9-824d-8b517ff25e0e)

---

## 🛠 **주요 기능**  

1. **회원 관리**  
   - 회원 가입 및 로그인  
   - 예약 정보와 얼굴 분석 기록 관리  

2. **샵 추천**  
   - 좋아요를 누른 메이크업 스타일 기반 추천  
   - **카카오맵 API**를 활용한 위치 정보 제공  

3. **샵 예약**  
   - 메이크업 샵 예약 및 분석 정보 공유 여부 선택  

4. **얼굴 분석**  
   - **VGG16 기반 딥러닝 모델**로 퍼스널 컬러 예측  
   - **OpenCV**로 눈 모양 유사도 분석  

---

## 🎨 **주요 기능 소개**  

### 1️⃣ **메이크업 샵 추천**  
![메이크업 샵 추천](https://github.com/user-attachments/assets/4ded9e91-0f66-48da-899e-10d99bd29e1a)

---

### 2️⃣ **퍼스널 컬러 예측**  
![퍼스널 컬러 예측](https://github.com/user-attachments/assets/96c317d3-2e6d-495a-9183-15ef01591dcc)

---

### 3️⃣ **눈 모양 분석**  
![눈 모양 분석](https://github.com/user-attachments/assets/85f4b5d8-ad14-45da-8531-613640c09e1b)  

이렇게 분석된 퍼스널 컬러, 눈 모양 그리고 인터넷에 공개 되어 있는 얼굴형 판별 모델을 활용해 크리에이터의 얼굴을 먼저 분석해 데이터베이스에 저장했고 웹캠을 통해 사용자 얼굴 이미지 획득 후 데이터를 비교 통해 매칭을 했습니다. 
![눈 모양 측정](https://github.com/user-attachments/assets/9986df54-2298-4ec5-a26f-fd97f232816a)  

<a href='https://huggingface.co/metadome/face_shape_classification'>사용된 얼굴형 판별 모델</a>

---

## 🏗 **서비스 아키텍처**  
![서비스 아키텍처](https://github.com/user-attachments/assets/5bc5d6c9-54cd-4433-b3ad-8987004015db)  

---

## 🚀 **서비스 흐름도**  
![서비스 흐름도](https://github.com/user-attachments/assets/087cee96-81fe-4e2b-9511-d98cd81249f9)  

---

## 🗂 **데이터베이스 설계**  
![ERD](https://github.com/user-attachments/assets/faab7cbf-3bee-438c-8975-588805ca0802)  

---

## 📚 **기술 스택**  

| 영역            | 기술 스택                                                                                                                                                                          |
|-----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Frontend**    | ![HTML](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white) ![CSS](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/javascript-%23F7DF1E.svg?style=for-the-badge&logo=javascript&logoColor=black) |
| **Backend**     | ![Spring Boot](https://img.shields.io/badge/springboot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white) ![Flask](https://img.shields.io/badge/flask-%23000000.svg?style=for-the-badge&logo=flask&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-%234479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) |
| **AI 기술**     | ![OpenCV](https://img.shields.io/badge/opencv-%235C3EE8.svg?style=for-the-badge&logo=opencv&logoColor=white) ![VGG16](https://img.shields.io/badge/CNN(VGG16)-%23000000.svg?style=for-the-badge&logo=CNN(VGG16)&logoColor=white) |
| **협업툴**      | ![GitHub](https://img.shields.io/badge/github-%23181717.svg?style=for-the-badge&logo=github&logoColor=white) ![Notion](https://img.shields.io/badge/notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white) ![Figma](https://img.shields.io/badge/figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white) |
| **IDE** | <img src="https://img.shields.io/badge/jupyter-%23F37626.svg?&style=for-the-badge&logo=jupyter&logoColor=white" /> <img src="https://img.shields.io/badge/VSCode-3477C6.svg?style=for-the-badge&logo=visual-studio-code&logoColor=3477C6" /> |


---

## ✨ **차별성 및 기대 효과**  

- **정교한 분석**: 얼굴형 및 퍼스널 컬러를 기반으로 맞춤형 추천 제공  
- **특화된 추천 시스템**:  
  - 눈 모양 유사도 분석을 통한 뷰티 크리에이터 추천  
  - 피부톤과 컬러를 고려한 메이크업 어드바이스 제공  
- **시간 및 비용 절약**: 맞춤형 추천으로 탐색 시간과 비용 절감  

---

## 🎯 **활용방안**  

- **남성 특화 뷰티 서비스 제공**: 얼굴형 분석을 통한 헤어스타일 및 미용실 추천 등 여성뿐만 아니라 남성에게 특화된 뷰티 서비스 시스템 도입
- **가상 메이크업 기능**:  
  - ‘좋아요'를 누른 메이크업을 사용자 얼굴에 입히는 기능 제공
- **제품 매칭 서비스**: ‘좋아요'를 누른 메이크업에 사용된 제품을 판매하는 서비스 제공

---

## 🧑‍🤝‍🧑 **팀원 소개**  
![팀원](https://github.com/user-attachments/assets/0f11b9dc-1f29-4e4f-abe6-934c7ec7ec3e)  

- 개인 git hub : 
<a href='https://github.com/nunuing'>조은유</a>, <a href='https://github.com/Jeon-Jeong-Hyeon'>전정현</a>, <a href='https://github.com/tw00rZer0'>이나영</a>, <a href='https://github.com/sajjibuger'>서지원</a>, <a href='https://github.com/Seo-ji-woo'>서지우</a>
---

**Find Your Own Beauty with MAKE YOU!** 🌸


