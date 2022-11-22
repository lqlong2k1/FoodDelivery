package com.LongLQ.FoodProject.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    boolean storeFile(MultipartFile file);
    Resource loadFileByName(String fileName);
}
