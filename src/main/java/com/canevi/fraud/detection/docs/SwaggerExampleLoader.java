package com.canevi.fraud.detection.docs;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SwaggerExampleLoader {

    public static String loadExample(String path) {
        try {
            Path filePath = new ClassPathResource(path).getFile().toPath();
            return Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Swagger example from: " + path, e);
        }
    }
}