package com.store.upload.controller;

import com.store.upload.service.IUploadService;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    @PostMapping("/image")
    public Result uploadImage(@RequestParam("file") MultipartFile file) {
        String url = uploadService.upload(file);
        if (StringUtils.isBlank(url)) {
            return new Result(false, StatusCode.ERROR, "上传失败");
        }
        return new Result(true, StatusCode.OK, "上传成功", url);
    }
}
