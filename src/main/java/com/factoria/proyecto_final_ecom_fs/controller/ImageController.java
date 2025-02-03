package com.factoria.proyecto_final_ecom_fs.controller;
import com.factoria.proyecto_final_ecom_fs.dto.ImageDTO;
import com.factoria.proyecto_final_ecom_fs.repository.ImageRepository;
import com.factoria.proyecto_final_ecom_fs.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(ImageDTO imageModel){
        try{
            return imageService.uploadImage(imageModel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}