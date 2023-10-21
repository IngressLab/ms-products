package com.example.ms_products.util;

import com.example.ms_products.exception.FileStorageException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileStorageUtil {

    private final Path fileUploadPath;
    public FileStorageUtil(){
        this.fileUploadPath = Paths.get("uploads/")
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileUploadPath);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory");
        }
    }
    public String storeFile(MultipartFile file){
       if(!file.isEmpty()) {

           try {
               Path targetLocation = fileUploadPath.resolve(file.getOriginalFilename());
               Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
               return file.getOriginalFilename();
           } catch (IOException ex) {
               throw new FileStorageException("Could not store file", ex);
           }
       }
       return null;
    }



}
