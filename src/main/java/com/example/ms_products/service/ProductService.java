package com.example.ms_products.service;

import com.example.ms_products.dao.repository.ProductRepository;
import com.example.ms_products.exception.FileStorageException;
import com.example.ms_products.model.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.ms_products.util.FileStorageUtil;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.example.ms_products.mapper.ProductMapper.productEntityMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final FileStorageUtil fileStorageUtil;

    public String saveImage(MultipartFile file){

       String fileStorageLocation = "${file.upload-dir}";
       Path fileUploadPath=Paths.get("uploads/")
               .toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileUploadPath);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
        try {
            Path targetLocation = fileUploadPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file.Please try again!", ex);
        }
        return file.getOriginalFilename();
    }

    public void saveProduct(MultipartFile file, ProductRequest request){

        var productEntity= productEntityMapper(request);

        String fileUpload=fileStorageUtil.storeFile(file);

        if(fileUpload !=null) productEntity.setImage(fileUpload);

        productRepository.save(productEntity);

    }


}
