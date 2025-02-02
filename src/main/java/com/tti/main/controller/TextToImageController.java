package com.tti.main.controller;

import com.tti.main.model.ImageRequest;
import com.tti.main.service.TextToImageService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextToImageController {
    @Autowired
    private TextToImageService textToImageService;

    @PostMapping("/get-image")
    public ResponseEntity<byte[]> getImage(@RequestBody ImageRequest imageRequest) {
        return textToImageService.getImage(imageRequest);
    }
}
