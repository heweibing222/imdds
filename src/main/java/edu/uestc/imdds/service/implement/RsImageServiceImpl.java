package edu.uestc.imdds.service.implement;


import edu.uestc.imdds.entitiy.RsImage;
import edu.uestc.imdds.entitiy.RsSubImage;
import edu.uestc.imdds.mapper.RsImageMapper;
import edu.uestc.imdds.service.HdfsService;
import edu.uestc.imdds.service.RsImageService;
import edu.uestc.imdds.service.RsSubImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import edu.uestc.imdds.service.parseMetadata.ParseGeneral;
import edu.uestc.imdds.service.parseMetadata.ParseLandsat;
import edu.uestc.imdds.service.parseMetadata.ParseXML;

import java.util.List;

@Service
public class RsImageServiceImpl implements RsImageService {

    @Value("${hdfs.path.rsImage.metadata}")
    private String metadata_upload_path;

    @Autowired
    private RsImageMapper rsImageMapper;
    @Autowired
    RsSubImageService rsSubImageService;
    @Autowired
    HdfsService hdfsService;
    @Autowired
    ParseLandsat parseLandsat;
    @Autowired
    ParseXML parseXML;
    @Autowired
    ParseGeneral parseGeneral;


    @Override
    public RsImage getById(Integer id){
        return rsImageMapper.getById(id);
    }
    @Override
    public RsImage getByName(String name) {
        return rsImageMapper.getByName(name);
    }
    @Override
    public  Boolean addMetadata(RsImage rsImage){
        // 禁止添加重名影像
        if(getByName(rsImage.getImageName()) != null){
            return false;
        }
        int flag = rsImageMapper.addRsImage(rsImage);
        return flag == 1;
    }

    @Override
    public Boolean deleteById(Integer id) {
        RsImage rsImage = rsImageMapper.getById(id);
        //删除HDFS-元数据
        if(!hdfsService.deleteFile(rsImage.getMetadataSavePath())){
            return false;
        };
        //删除HDFS-实体数据
        if(!hdfsService.deleteFile(rsImage.getImageSavePath())){
            return false;
        };
        //删除MySQL-元数据
        if(rsImageMapper.deleteById(id) == 0){
            return false;
        };
        //删除MySQL-子图像
        if(!rsSubImageService.deleteByFatherId(id)){
            return false;
        };
        return true;
    }

    @Override
    public Boolean updateRsImage(RsImage rsImage) {
        if(getById(rsImage.getId()) == null){
            return false;
        }
        int flag = rsImageMapper.updateRsImage(rsImage);
        return flag == 1;
    }

    @Override
    public List<RsImage> getAll() {
        return rsImageMapper.getAll();
    }

    @Override
    public RsImage parseMetadata(String filePath, String satelliteType) {
        if(satelliteType.equals("landsat5")||satelliteType.equals("landsat7")||satelliteType.equals("landsat8")){
            return parseLandsat.parse(filePath,satelliteType);
        }else if (satelliteType.equals("zy3")||satelliteType.equals("gaofen")) {
            return parseXML.parse(filePath,satelliteType);
        }else {
            return parseGeneral.parse(filePath,satelliteType);
        }
    }

    @Override
    public Boolean addRsImage(MultipartFile metadataFile, String satelliteType, MultipartFile[] imageFiles) {
        //元数据文件处理
        String uploadDir = metadata_upload_path+'/'+satelliteType;
        //元数据文件上传到HDFS
        if(!hdfsService.uploadFile(metadataFile,uploadDir)){return false;}
        //元数据文件解析
        String filePath = uploadDir+'/'+metadataFile.getOriginalFilename();
        RsImage rsImage = parseMetadata(filePath, satelliteType);
        //元数据文件上传到MySQL
        if (rsImageMapper.addRsImage(rsImage) == 0) {return false;}
        //子图像上传
        for (int i = 0; i < imageFiles.length; i++) {
            if(!hdfsService.uploadFile(imageFiles[i],rsImage.getImageSavePath())){return false;}
            RsSubImage subImage = new RsSubImage();
            subImage.setFatherId(rsImageMapper.getByName(rsImage.getImageName()).getId());
            subImage.setImageName(imageFiles[i].getOriginalFilename());
            subImage.setImageSavePath(rsImage.getImageSavePath()+"/"+imageFiles[i].getOriginalFilename());
            if(!rsSubImageService.add(subImage)){return false;}
        }
        return true;
    }


}
