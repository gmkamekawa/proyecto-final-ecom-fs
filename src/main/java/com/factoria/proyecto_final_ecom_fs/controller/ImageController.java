package com.factoria.proyecto_final_ecom_fs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/images-database")
public class ImageController {

    @GetMapping
    public ResponseEntity<List<String>> getImages() {
        File folder = new File("src/main/resources/static/images-database");
        File[] files = folder.listFiles();
        if (files != null) {
            List<String> imagePaths = new ArrayList<>();
            for (File file : files) {
                if (file.isFile()) {
                    imagePaths.add("images-database/" + file.getName());

                }
            }
            return ResponseEntity.ok(imagePaths);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
    }
}
