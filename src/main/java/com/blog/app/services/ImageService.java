package com.blog.app.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface ImageService {
    String saveImage(MultipartFile image,String path);
    InputStream getResource(String path, String name) throws FileNotFoundException;

}
