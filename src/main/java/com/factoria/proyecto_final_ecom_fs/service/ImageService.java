package com.factoria.proyecto_final_ecom_fs.service;


import com.factoria.proyecto_final_ecom_fs.dto.ImageDTO;
import com.factoria.proyecto_final_ecom_fs.model.Image;
import com.factoria.proyecto_final_ecom_fs.repository.ImageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final CloudinaryService cloudinaryService;

    public ImageService(ImageRepository imageRepository, CloudinaryService cloudinaryService){
        this.imageRepository= imageRepository;
        this.cloudinaryService=cloudinaryService;
    }

    public ResponseEntity<Map> uploadImage(ImageDTO imageModel){
        try{
            Image image = new Image();
            image.setName(imageModel.getName());
            image.setUrl(cloudinaryService.uploadImage(imageModel.getFile(),"folder_1"));
            if(image.getUrl()==null){
                return  ResponseEntity.badRequest().build();
            }
            imageRepository.save(image);
            return ResponseEntity.ok().body(Map.of("url",image.getUrl()));
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}