import base64
from io import BytesIO
from flask import Flask, request, jsonify
from PIL import Image
from transformers import AutoModelForImageClassification, AutoProcessor
from tensorflow.keras.models import load_model
import numpy as np
from flask_cors import CORS

app = Flask(__name__)
CORS(app, resources={r"/analyze": {"origins": "http://localhost:8089"}})

# 모델 및 프로세서 로드
MODEL_NAME = "metadome/face_shape_classification"
processor = AutoProcessor.from_pretrained(MODEL_NAME)

face_shape_model = AutoModelForImageClassification.from_pretrained(MODEL_NAME)
personal_color_model = load_model("model/best_model.keras")

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
        face_shape, face_confidence = analyze_face_shape(image)
        # 퍼스널컬러 분석
        personal_color, color_confidence = analyze_personal_color(image)
        
        # 결과 반환
        return jsonify({
            "faceShape": {
                "label" : face_shape,
                "confidence": face_confidence
            },
            "personalColor":{
                "label": personal_color,
                "confidence": color_confidence
            }    
        })
    except Exception as e:
        print(f"Error: {e}")
        return jsonify({"error": "분석 실패"}), 500

if __name__ == '__main__':
    app.run(debug=True)