package com.bangbumdae.makeu.controller;

import java.util.Base64;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageRestController {

    @PostMapping("/upload")
    public String handleImageUpload(@RequestBody ImageRequest request) {
        String base64Image = request.getImage();

        // Base64 데이터 처리
        byte[] imageBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);
        // TODO: 이미지 저장 또는 분석

        return "이미지 처리 완료";
    }

    public static class ImageRequest {
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
