package com.tti.main.service;

import com.tti.main.model.ImageRequest;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class TextToImageService {

    @Autowired
    private ImageModel stabilityaiImageModel;

    public ResponseEntity<byte[]> getImage(ImageRequest imageRequest) {
        ImageResponse response = stabilityaiImageModel.call(
                new ImagePrompt(imageRequest.getUserMessage(),
                        StabilityAiImageOptions.builder()
                                .stylePreset(imageRequest.getStyle())
                                .N(4)
                                .height(1024)
                                .width(1024).build())
        );

        String base64Image = response.getResult().getOutput().getB64Json();
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
