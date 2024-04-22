package edu.uestc.imdds.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HdfsService {
    boolean makeFolder(String path);
    boolean existFile(String path);
    boolean deleteFile(String path);
    boolean uploadFile(MultipartFile file, String uploadDir);
    boolean downloadFile(String path, String downloadPath);
    boolean copyFile(String sourcePath, String targetPath);
    //阅读TXT文件，返回一个List
    List<String> readTxtFile(String filePath);
}
