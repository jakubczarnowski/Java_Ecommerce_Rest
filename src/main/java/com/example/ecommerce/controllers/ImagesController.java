package com.example.ecommerce.controllers;

import com.example.ecommerce.Utils.GenerateRandomString;
import com.example.ecommerce.service.ImagesStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/static/")
public class ImagesController {
    ImagesStorageService imagesStorageService;

    public ImagesController(ImagesStorageService imagesStorageService) {
        this.imagesStorageService = imagesStorageService;
    }

    @PostMapping(value="upload", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile file) {
        String fileName = GenerateRandomString.generateRandomString(20) + "." + file.getOriginalFilename().split("\\.")[1];
        try {
            imagesStorageService.save(file, fileName);
            return new ResponseEntity<>(fileName, HttpStatus.CREATED);
        } catch (Exception ex) {

            return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "{image_url}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String image_url) throws IOException {
        byte[] bytes = imagesStorageService.getImage(image_url);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
}