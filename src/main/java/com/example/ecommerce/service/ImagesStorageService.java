package com.example.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImagesStorageService {
    String resourcePath = "/var/lib/ecommerceImages/"; // TODO change for macos, currently saving in root path

    public void save(MultipartFile file, String imageName) throws IOException {
        // Files storage in a local directory
        Files.createDirectories(Paths.get(resourcePath));
        Path path = Paths.get(resourcePath + imageName);
        try {
            Files.copy(file.getInputStream(), path);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public void deleteAll(List<String> fileNames) {
        //todo
    }

    public byte[] getImage(String imageName) throws IOException {
        Path path = Paths.get(resourcePath + imageName);
        byte[] arr = Files.readAllBytes(path);
        return arr;

    }
}
