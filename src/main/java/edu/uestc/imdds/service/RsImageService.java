package edu.uestc.imdds.service;

import edu.uestc.imdds.entitiy.RsImage;
import edu.uestc.imdds.entitiy.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RsImageService {

    RsImage getById(Integer id);

    RsImage getByName(String name);

    Boolean addMetadata(RsImage rsImage);

    Boolean deleteById(Integer id);

    Boolean updateRsImage(RsImage rsImage);

    List<RsImage> getAll();

    RsImage parseMetadata(String filePath,String satelliteType);
    //完整的上传影像流程
    Boolean addRsImage(MultipartFile metadataFile, String satelliteType, MultipartFile[] imageFiles);

}
