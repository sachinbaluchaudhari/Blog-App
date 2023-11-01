package com.blog.app.services.serviceImpl;

import com.blog.app.dto.ApiResponse;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public String saveImage(MultipartFile image, String path) {
        String originalFilename = image.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println("extention: "+extension);
        String imageName = UUID.randomUUID().toString() + "." + extension;
        System.out.println("image name: "+imageName);
        String fullPath = path + File.separator + imageName;
        if (extension.equalsIgnoreCase(".jpg")||extension.equalsIgnoreCase(".png")||extension.equalsIgnoreCase(".jpeg"))
        {
            File folder=new File(path);
            if (!folder.exists())
            {
                folder.mkdirs();
            }
            try {
                long copy = Files.copy(image.getInputStream(), Paths.get(fullPath));
            }catch (Exception ex)
            {

            }
            return imageName;



        }
        else {
            throw  new ResourceNotFoundException("Invalid file extension!!");
        }

    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath=path+File.separator+name;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}
