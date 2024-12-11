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
processor = AutoProcessor.from_pretrained(MODEL_NAME)

face_shape_model = AutoModelForImageClassification.from_pretrained(MODEL_NAME)
personal_color_model = load_model("./model/best_model.keras")

@app.route('/')
def test():
    print('test')
    return 'test222'


def analyze_face_shape(image_base64):
    # Base64 이미지를 PIL 이미지로 변환
    image_data = base64.b64decode(image_base64.split(",")[1])  # 'data:image/png;base64,...' 부분 제거 후 디코딩
    image = Image.open(BytesIO(image_data)).convert("RGB")

    # 이미지를 모델 입력 형식으로 변환
    inputs = processor(images=image, return_tensors="pt")

    # 모델 추론
    outputs = face_shape_model(**inputs)
    logits = outputs.logits
    predicted_class = logits.argmax(-1).item()

    # 라벨과 신뢰도 추출
    label = face_shape_model.config.id2label[predicted_class]
    confidence = float(logits.softmax(-1).max().item())

    return label, confidence

def analyze_personal_color(image_base64):
    # Base64 이미지를 PIL 이미지로 변환
    image_data = base64.b64decode(image_base64.split(",")[1])
    image = Image.open(BytesIO(image_data)).convert("RGB").resize((64, 64))  # 입력 크기 조정

    # 이미지를 NumPy 배열로 변환
    image_array = np.array(image) / 255.0  # 정규화
    image_array = np.expand_dims(image_array, axis=0)  # 배치 차원 추가

    # 퍼스널컬러 모델 추론
    predictions = personal_color_model.predict(image_array)
    predicted_class = np.argmax(predictions)
    confidence = float(np.max(predictions))

    # 클래스 라벨 정의
    class_labels = ["Spring", "Summer", "Autumn", "Winter"]  # 퍼스널컬러 분류 라벨
    label = class_labels[predicted_class]

    return label, confidence

@app.route('/analyze', methods=['POST'])
def analyze():
    data = request.get_json()
    gender = data.get('gender')
    image = data.get('image')  # Base64 인코딩된 이미지 데이터

    try:
        # 얼굴형 분석
        print('test1')
        face_shape, face_confidence = analyze_face_shape(image)
        # 퍼스널컬러 분석
        print('test2')
        personal_color, color_confidence = analyze_personal_color(image)
        # 눈생김새 분석
        print('test3')
        yt_eye = analyze_eye(image)
        
        
        # 결과 반환
        return jsonify({
            "faceShape": {
                "label" : face_shape,
                "confidence": face_confidence
            },
            "personalColor":{
                "label": personal_color,
                "confidence": color_confidence
            },
            "eye":{
                "label" : yt_eye
            }    
        });
    except Exception as e:
        print(f"Error: {e}")
        return jsonify({"error": "분석 실패"}), 500




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

man_list = []
woman_list = []

for number in range(len(man_result)) :
    man_list.append(man_result[number][1])

for number2 in range(len(woman_result)) :
    woman_list.append(woman_result[number2][1])

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

def analyze_eye(image_base64):

    # gender = data.get('gender')
    gender = 'female'

    # Base64 이미지를 PIL 이미지로 변환
    image_data = base64.b64decode(image_base64.split(",")[1])  # 'data:image/png;base64,...' 부분 제거 후 디코딩
    #image = Image.open(BytesIO(image_data)).convert("RGB")
    
    np_arr = np.frombuffer(image_data, np.uint8)
    # OpenCV 이미지로 디코딩
    image = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)

    
    # MediaPipe 초기화
    mp_face_mesh = mp.solutions.face_mesh

        # MediaPipe FaceMesh 사용
    with mp.solutions.face_mesh.FaceMesh(refine_landmarks=True) as face_mesh:
            # 얼굴 랜드마크 탐지
        rgb_image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
        results = face_mesh.process(rgb_image)

        if results.multi_face_landmarks:
            for face_landmarks in results.multi_face_landmarks:
                    # 양쪽 눈 중심 좌표 계산
                left_eye_points = [face_landmarks.landmark[i] for i in [33, 133]]
                right_eye_points = [face_landmarks.landmark[i] for i in [362, 263]]

                    # 얼굴 정렬
                aligned_image, left_eye_center, right_eye_center = align_face_with_landmarks(
                    image, left_eye_points, right_eye_points
                )

                rgb_image = cv2.cvtColor(aligned_image, cv2.COLOR_BGR2RGB)
                results = face_mesh.process(rgb_image)                

                if results.multi_face_landmarks:
                    for face_landmarks in results.multi_face_landmarks:  
                        
                            # 정렬된 이미지에서 랜드마크 각도 계산
                        
                        for eye_name, landmark_indices in eye_landmark_sets.items():
                            points = [
                                (
                                    int(face_landmarks.landmark[i].x * aligned_image.shape[1]),
                                    int(face_landmarks.landmark[i].y * aligned_image.shape[0])
                                )
                                for i in landmark_indices
                            ]
                            angle = calculate_angle(points[0], points[1], points[2])
                            values = round(angle, 2)  # 각도를 저장

                            # returnList = findNearNum(values)

                            # resemble_yt = man_result[returnList[0]][0]
                        
                        # 결과 저장
                        return find_nearest_values(values, gender)


def find_nearest_values(target, gender):
    
    if gender == 'male':
        selected_list = man_list
    elif gender == 'female':
        selected_list = woman_list
    
    # 리스트 값과 인덱스를 함께 저장
    indexed_lst = [(value, idx) for idx, value in enumerate(selected_list)]
    
    # 리스트를 기준값과의 절대 차이로 정렬
    indexed_lst_sorted = sorted(indexed_lst, key=lambda x: abs(x[0] - target))
    
    # 기준값보다 큰 값과 작은 값을 분리
    greater = [item for item in indexed_lst_sorted if item[0] > target]
    smaller = [item for item in indexed_lst_sorted if item[0] < target]
    
    # 결과 조합: 더 큰 값 2개와 더 작은 값 1개
    result = greater[:2] + smaller[:1]
    return result
    

 



if __name__ == '__main__':
    app.run(debug=True, port=5000)


