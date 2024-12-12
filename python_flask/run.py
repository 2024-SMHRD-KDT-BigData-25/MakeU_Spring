import base64
from io import BytesIO
from flask import Flask, request, jsonify
from PIL import Image
from transformers import AutoModelForImageClassification, AutoProcessor
from tensorflow.keras.models import load_model
import numpy as np
from flask_cors import CORS
import cv2
import mediapipe as mp
import math

app = Flask(__name__)
CORS(app, resources={r"/analyze": {"origins": "http://localhost:8089"}})

# 모델 및 프로세서 로드
MODEL_NAME = "metadome/face_shape_classification"
processor = AutoProcessor.from_pretrained(MODEL_NAME )

face_shape_model = AutoModelForImageClassification.from_pretrained(MODEL_NAME)
personal_color_model = load_model("./model/best_model.keras")

@app.route('/')
def test():
    print('test')
    return 'test222'



def analyze_face_shape(image_base64):
    # Base64 이미지를 PIL 이미지로 변환
    # image_data = base64.b64decode(image_base64.split(",")[1])  # 'data:image/png;base64,...' 부분 제거 후 디코딩
    # image = Image.open(BytesIO(image_data)).convert("RGB") 
    image = image_base64
    # 이미지를 모델 입력 형식으로 변환
    inputs = processor(images=image, return_tensors="pt")

    # 모델 추론
    outputs = face_shape_model(**inputs)
    logits = outputs.logits
    predicted_class = logits.argmax(-1).item()
    print("shape")
    # 라벨과 신뢰도 추출
    label = face_shape_model.config.id2label[predicted_class]
    confidence = float(logits.softmax(-1).max().item())

    return label, confidence

def analyze_personal_color(image_base64):
    # Base64 이미지를 PIL 이미지로 변환
    # image_data = base64.b64decode(image_base64.split(",")[1])
    # image = Image.open(BytesIO(image_data)).convert("RGB").resize((64, 64))  # 입력 크기 조정
    face_img_rgb = cv2.cvtColor(image_base64, cv2.COLOR_BGR2RGB)  # BGR -> RGB 변환
    pil_image = Image.fromarray(face_img_rgb)

    image = pil_image.resize((64, 64))
    print("이미지 리사이즈")
    # 이미지를 NumPy 배열로 변환
    image_array = np.array(image) / 255.0  # 정규화
    print(image_array.flags)
    image_array = np.expand_dims(image_array, axis=0)  # 배치 차원 추가
    print("psc")
    # 퍼스널컬러 모델 추론
    predictions = personal_color_model.predict(image_array)
    predicted_class = np.argmax(predictions)
    confidence = float(np.max(predictions))

    # 클래스 라벨 정의
    class_labels = ["Spring", "Summer", "Autumn", "Winter"]  # 퍼스널컬러 분류 라벨
    label = class_labels[predicted_class]

    return label, confidence






man_result = [['HOONION 후니언', 133.99],
 ['공주는 외로버', 155.53],
 ['관리는 하고 살자', 136.22],
 ['관리하는 남자 아우라M', 133.58],
 ['규채널', 136.02],
 ['레오제이', 129.88],
 ['박쿠쿠 kookoo', 124.89],
 ['비트 BTE', 138.93],
 ['션님 SHAWN', 139.73],
 ['스완SWAN_현실남자뷰티', 132.57],
 ['신성호 Makeup', 131.46],
 ['임파 IMPA', 137.61],
 ['죠셉Joseph', 132.11],
 ['채우 Chaewoo', 126.56],
 ['피부는 민동성', 137.27],
 ['한스 HANS', 129.31],
 ['해피가이정호', 127.72],
 ['호박스Hobox', 136.38],
 ["화니 HWAN'E", 133.21]]

woman_result = [['10시엔 디붕', 122.7],
 ['Arang아랑', 132.06],
 ['areumsongee아름송이', 131.78],
 ['bbomni 뽐니', 131.11],
 ['Happyrim 혜림쌤', 130.14],
 ['INBORA 인보라', 128.42],
 ['kyung Sun', 129.75],
 ['Saerom Min개코의 오픈스튜디오', 137.34],
 ['Yoo True', 132.06],
 ['경선', 129.04],
 ['고우리 Go uri', 125.36],
 ['귄펭 GUINPEN', 132.43],
 ['김나연', 128.45],
 ['김지유 JIYU KIM', 135.8],
 ['김크리스탈 KimCrystal', 131.27],
 ['깡나', 121.43],
 ['꼬유진', 128.99],
 ['꽁지', 128.41],
 ['다영', 133.8],
 ['라뮤끄', 128.42],
 ['리수', 127.76],
 ['몌 myerry', 123.09],
 ['무쌍이다빈 DaBeen', 117.44],
 ['민카롱', 148.76],
 ['밤비걸', 113.5],
 ['뷰드름 유튜버 인씨', 121.35],
 ['소윤Soyoon', 131.63],
 ['시네', 122.97],
 ['써니', 131.1],
 ['쏭냥', 133.97],
 ['씬님', 139.11],
 ['연두콩', 129.01],
 ['오션 OCEAN', 130.26],
 ['우린', 128.09],
 ['유깻잎', 131.82],
 ['유채 YUCHAE', 128.57],
 ['유화이', 128.48],
 ['윤쨔미 YoonCharmi', 125.41],
 ['이별미 Byeolmii', 134.24],
 ['이사배', 123.9],
 ['제이미포유', 132.4],
 ['조효진', 134.13],
 ['지냐 Jinyaa', 135.27],
 ['쩡유 JJeong U', 125.81],
 ['킴닥스', 127.55],
 ['하이예나', 128.42],
 ['혜리미 HTERIMI', 114.71],
 ['홀리 holy', 136.24],
 ['회사원', 127.33],
 ['효진조 Hyojin Cho', 136.29],
 ['효현', 122.68]]


# OpenCV로 이미지 읽기
def process_image(image_base64):

    mp_face_mesh = mp.solutions.face_mesh
    face_mesh = mp_face_mesh.FaceMesh(min_detection_confidence=0.5, min_tracking_confidence=0.5)
    
    # Base64 이미지를 PIL 이미지로 변환
    image_data = base64.b64decode(image_base64.split(",")[1])  # 'data:image/png;base64,...' 부분 제거 후 디코딩
    #image = Image.open(BytesIO(image_data)).convert("RGB")
    
    np_arr = np.frombuffer(image_data, np.uint8)
    # OpenCV 이미지로 디코딩
    img = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)
    
    # 이미지 가져오기
    img_rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)

    # 얼굴 랜드마크 예측
    results = face_mesh.process(img_rgb)

    # MediaPipe Face Mesh 모델 초기화


    if results.multi_face_landmarks:
        # 얼굴이 감지되었을 경우
        for face_landmarks in results.multi_face_landmarks:
            # 얼굴 윤곽을 추출 (주로 0~17번 랜드마크)
            face_coords = []
            face_point = [10 , 356, 152, 127]
            for i in face_point:  # 얼굴 윤곽 (0~16번)
                x = int(face_landmarks.landmark[i].x * img.shape[1])
                y = int(face_landmarks.landmark[i].y * img.shape[0])
                face_coords.append((x, y))

            for point in face_coords:
                cv2.circle(img, point, 2, (0, 255, 0), -1)  # 초록색 점   

            # cv2.imshow("Eye Angle Visualization", img)
            # cv2.waitKey(0)
            # cv2.destroyAllWindows()            

            # 얼굴 윤곽에 사각형을 그려 얼굴 영역 자르기
            x_min = min(face_coords, key=lambda x: x[0])[0]-40
            x_max = max(face_coords, key=lambda x: x[0])[0]+40
            y_min = min(face_coords, key=lambda x: x[1])[1]-55
            y_max = max(face_coords, key=lambda x: x[1])[1]+40

            # 얼굴 영역 자르기
            face_img = img[y_min:y_max, x_min:x_max]          

            return face_img

# 각도 계산 함수
def calculate_angle(pointA, pointB, pointC):
    AB = np.array([pointA[0] - pointB[0], pointA[1] - pointB[1]])
    BC = np.array([pointC[0] - pointB[0], pointC[1] - pointB[1]])

    dot_product = np.dot(AB, BC)
    magnitude_AB = np.linalg.norm(AB)
    magnitude_BC = np.linalg.norm(BC)

    angle_radian = np.arccos(dot_product / (magnitude_AB * magnitude_BC))
    angle_degree = np.degrees(angle_radian)

    return angle_degree

# 얼굴 정렬 함수 수정 (기준 좌표 반환)
def align_face_with_landmarks(image, left_eye_points, right_eye_points):
    left_eye_center = (
        int(sum([p.x for p in left_eye_points]) / len(left_eye_points) * image.shape[1]),
        int(sum([p.y for p in left_eye_points]) / len(left_eye_points) * image.shape[0])
    )
    right_eye_center = (
        int(sum([p.x for p in right_eye_points]) / len(right_eye_points) * image.shape[1]),
        int(sum([p.y for p in right_eye_points]) / len(right_eye_points) * image.shape[0])
    )

    aligned_image = align_face(image, left_eye_center, right_eye_center)
    return aligned_image, left_eye_center, right_eye_center

# 얼굴 정렬 함수
def align_face(image, left_eye_center, right_eye_center):
    dx = right_eye_center[0] - left_eye_center[0]
    dy = right_eye_center[1] - left_eye_center[1]
    angle = np.degrees(np.arctan2(dy, dx))

    h, w = image.shape[:2]
    eyes_center = ((left_eye_center[0] + right_eye_center[0]) // 2,
                   (left_eye_center[1] + right_eye_center[1]) // 2)
    rot_matrix = cv2.getRotationMatrix2D(eyes_center, angle, 1.0)
    aligned_image = cv2.warpAffine(image, rot_matrix, (w, h), flags=cv2.INTER_LINEAR)
    return aligned_image

eye_landmark_sets = {
    "Left Eye": [133, 159, 33]
}

def analyze_eye(face_img):
    try:
        with mp.solutions.face_mesh.FaceMesh(refine_landmarks=True) as face_mesh:
            rgb_image = cv2.cvtColor(face_img, cv2.COLOR_BGR2RGB)
            results = face_mesh.process(rgb_image)

            if results.multi_face_landmarks:
                for face_landmarks in results.multi_face_landmarks:
                    left_eye_points = [face_landmarks.landmark[i] for i in [33, 133]]
                    right_eye_points = [face_landmarks.landmark[i] for i in [362, 263]]

                    aligned_image, left_eye_center, right_eye_center = align_face_with_landmarks(
                        face_img, left_eye_points, right_eye_points
                    )

                    rgb_image = cv2.cvtColor(aligned_image, cv2.COLOR_BGR2RGB)
                    results = face_mesh.process(rgb_image)

                    if results.multi_face_landmarks:
                        for face_landmarks in results.multi_face_landmarks:
                            for eye_name, landmark_indices in eye_landmark_sets.items():
                                points = [
                                    (
                                        int(face_landmarks.landmark[i].x * aligned_image.shape[1]),
                                        int(face_landmarks.landmark[i].y * aligned_image.shape[0])
                                    )
                                    for i in landmark_indices
                                ]

                                for point in points:
                                    cv2.circle(aligned_image, point, 2, (0, 255, 0), -1)  # 초록색 점
                                # 점을 잇는 선 시각화
                                for i in range(len(points) - 1):
                                    cv2.line(aligned_image, points[i], points[i + 1], (255, 0, 0), 1)  # 파란색 선

                                angle = calculate_angle(points[0], points[1], points[2])
                             
                                print(angle)
                                return round(angle, 2)
        return "Eye landmarks not detected"
    except Exception as e:
        print(f"눈 분석 실패: {e}")
        return "Error analyzing eye"
   

@app.route('/analyze', methods=['POST'])
def analyze():
    data = request.get_json()
    gender = data.get('gender')
    image = process_image(data.get('image'))  # Base64 인코딩된 이미지 데이터
    image2 = data.get('image')

    try:
        # 얼굴형 분석
        print('test1')
        face_shape, face_confidence = analyze_face_shape(image)
        print(face_shape)
        # 퍼스널컬러 분석
        print('test2')
        personal_color, color_confidence = analyze_personal_color(image)
        # 눈생김새 분석
        # print('test3')
        eye_shape = analyze_eye(image)
        print('test3')
        
        
        # 결과 반환
        return jsonify({
            "faceShape": {
                "label" : face_shape,
                "confidence" : face_confidence
            },
            "personalColor":{
                "label" : personal_color,
                "confidence" : color_confidence
            },
            "eyeShape": {
            "label" : eye_shape if eye_shape else "Not Detected"
            }
        })
    except Exception as e:
        print(f"Error: {e}")
        return jsonify({"error": "분석 실패"}), 500
    

if __name__ == '__main__':
    app.run(debug=True, port=5000)


