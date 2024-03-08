package com.example.springaidemo.service;

import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class AIImageService {

    private final OpenAiImageClient imageClient;

    public AIImageService(OpenAiImageClient imageClient) {
        this.imageClient = imageClient;
    }

    public Image getImage() {

        ImagePrompt imagePrompt = new ImagePrompt("""
                A light cream colored mini golden doodle 
                """, OpenAiImageOptions.builder()
                .withQuality("hd")
                .withN(4)
                .withHeight(1024)
                .withWidth(1024)
                .build());
        ImageResponse imageResponse = imageClient.call(imagePrompt);
        return imageResponse.getResult().getOutput();
    }
}
