package edu.uestc.imdds.service.implement;

import edu.uestc.imdds.service.HdfsService;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class HdfsServiceImpl implements HdfsService {
    private static final int bufferSize = 1024 * 1024 * 64;
    @Autowired
    private FileSystem fileSystem;
    @Override
    public boolean makeFolder(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        if (existFile(path)) {
            return true;
        }
        Path src = new Path(path);
        try {
            return fileSystem.mkdirs(src);
        } catch (IOException e) {
//            log.error(e.getMessage());
        }
        return false;
    }
    @Override
    public boolean existFile(String path) {
        if (StringUtils.isEmpty(path)){
            return false;
        }
        Path src = new Path(path);
        try {
            return fileSystem.exists(src);
        } catch (IOException e) {
//            log.error(e.getMessage());
        }
        return false;
    }
    @Override
    public boolean deleteFile(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        if (!existFile(path)) {
            //不存在即删除成功
            return true;
        }
        Path src = new Path(path);
        try {
            return fileSystem.delete(src);
        } catch (IOException e) {
//            log.error(e.getMessage());
        }
        return false;
    }
    @Override
    public boolean uploadFile(MultipartFile file, String uploadDir) {
        if (StringUtils.isEmpty(uploadDir)) {
            return false;
        }
        try {
            if (!fileSystem.exists(new Path(uploadDir))) {
                fileSystem.mkdirs(new Path(uploadDir));
            }
            String uploadPath = uploadDir+'/'+file.getOriginalFilename();
            if(fileSystem.exists(new Path(uploadPath))){
                System.out.println("待上传文件已存在，无法上传！");
                return false;
            }
            FSDataOutputStream out= fileSystem.create(new Path(uploadPath));
            IOUtils.copyBytes(file.getInputStream(), out, fileSystem.getConf());
            return existFile(uploadPath);
        } catch (IOException e) {
//            log.error(e.getMessage(), e);
        }
        return false;
    }
    @Override
    public boolean downloadFile(String path, String downloadPath) {
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(downloadPath)) {
            return false;
        }
        Path clienPath = new Path(path);
        Path targetPath = new Path(downloadPath);
        try {
            fileSystem.copyToLocalFile(false,clienPath, targetPath);
            return true;
        } catch (IOException e) {
//            log.error(e.getMessage());
        }
        return false;
    }
    @Override
    public boolean copyFile(String sourcePath, String targetPath) {
        if (StringUtils.isEmpty(sourcePath) || StringUtils.isEmpty(targetPath)) {
            return false;
        }
        Path oldPath = new Path(sourcePath);
        Path newPath = new Path(targetPath);
        FSDataInputStream inputStream = null;
        FSDataOutputStream outputStream = null;

        try {
            inputStream = fileSystem.open(oldPath);
            outputStream = fileSystem.create(newPath);
            IOUtils.copyBytes(inputStream,outputStream,bufferSize,false);
            return true;
        } catch (IOException e) {
//            log.error(e.getMessage());
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
//                    log.error(e.getMessage());
                }
            }
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
//                    log.error(e.getMessage());
                }
            }
        }
        return false;
    }
    @Override
    public List<String> readTxtFile(String filePath) {
        List<String> list = new ArrayList<>();
        try {
            FSDataInputStream hdfsInStream = fileSystem.open(new Path(filePath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(hdfsInStream));
            String lineTxt = null;
            while ((lineTxt = reader.readLine()) != null) {
                list.add(lineTxt);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


}
