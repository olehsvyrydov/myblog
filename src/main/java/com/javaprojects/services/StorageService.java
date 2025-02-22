package com.javaprojects.services;

import org.springframework.web.multipart.MultipartFile;

import java.net.URI;


public interface StorageService {

    String handleFileUpload(MultipartFile file);

    public URI getRootLocation();

    public String getImageDirectory();

    public String getDefaultFilePath();
}
