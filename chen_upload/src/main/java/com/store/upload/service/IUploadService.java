package com.store.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    String upload(MultipartFile file);
}
