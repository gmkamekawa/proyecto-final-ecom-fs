package com.factoria.proyecto_final_ecom_fs.dto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageDTO {
    private String name;
    private MultipartFile file;

    public ImageDTO(){

    }
    public ImageDTO(String name, MultipartFile file){
        this.name=name;
        this.file=file;
    }
}