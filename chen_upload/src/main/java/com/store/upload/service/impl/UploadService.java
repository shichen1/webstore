package com.store.upload.service.impl;

import com.store.upload.service.IUploadService;
import com.store.upload.utils.OSSClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class UploadService implements IUploadService {

    // 设定支持的文件类型
    private static final List<String> suffixes = Arrays.asList("image/png","image/jpeg");

    @Value("${aliyun.oss.bucketName}")
    public String bucketName;

    @Value("${aliyun.oss.folder}")
    public String folder;

    @Override
    public String upload(MultipartFile file) {
        try {
            // 1. 图片信息校验
            // 1.1 校验文件类型
            String type = null;

            type = file.getContentType();

            if (!suffixes.contains(type)) {
                throw new RuntimeException("上传失败,文件类型不匹配");
            }

            // 1.2 校验图片内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new RuntimeException("上传失败,文件内容不符合要求");
            }

            Map<String, String> map = OSSClientUtil.uploadObject2OSS(OSSClientUtil.getOSSClient(), file, bucketName, folder);
            String resultUrl = map.get("resultUrl");

            // 2. 保存图片
            return resultUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
