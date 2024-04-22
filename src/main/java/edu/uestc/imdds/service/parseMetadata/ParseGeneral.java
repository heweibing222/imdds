package edu.uestc.imdds.service.parseMetadata;

import edu.uestc.imdds.Utils.Utils;
import edu.uestc.imdds.entitiy.RsImage;
import edu.uestc.imdds.service.HdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParseGeneral {
    @Autowired
    private HdfsService hdfsService;
    @Value("${hdfs.path.rsImage.imageData}")
    private String imageData_upload_path;
    public RsImage parse(String filePath, String satelliteType){
        List<String> list = hdfsService.readTxtFile(filePath);
        String a1;
        String a2;
        String a3;
        String a4;
        String a5;
        String a6;
        String a7;
        String a8;
        String a9;
        String a10;
        String a11;
        String a12;
        String a13;
        String a14;
        String a15;
        String a16;
        String a17;
        String a18;

        a1=list.get(0).split("=")[1];
        a2=list.get(1).split("=")[1];
        a3=list.get(2).split("=")[1];
        a4=list.get(3).split("=")[1];
        a5=list.get(4).split("=")[1];
        a6=list.get(5).split("=")[1];
        a7=list.get(6).split("=")[1];
        a8=list.get(7).split("=")[1];
        a9=list.get(8).split("=")[1];
        a10=list.get(9).split("=")[1];
        a11=list.get(10).split("=")[1];
        a12=list.get(11).split("=")[1];
        a13=list.get(12).split("=")[1];
        a14=list.get(13).split("=")[1];
        a15=list.get(14).split("=")[1];
        a16=filePath;
        a17=imageData_upload_path+'/'+satelliteType+'/'+a1;
        a18=Utils.getNowDate();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id", Utils.getTimeMillis());
        map.put("imageName",a1.trim());
        map.put("satellite",a2.trim());
        map.put("sensor",a3.trim());
        map.put("shootDate",a4.trim().replaceAll("\\..*$", ""));
        map.put("shootTime",a5.trim().replaceAll("\\..*$", ""));
        //将经纬度字字符串转换为统一的格式，方便数据库的比较查询
        map.put("topLeftLatitude",a8.trim());
        map.put("topLeftLongitude",a9.trim());
        map.put("topRightLatitude",a10.trim());
        map.put("topRightLongitude",a11.trim());
        map.put("bottomRightLatitude",a12.trim());
        map.put("bottomRightLongitude",a13.trim());
        map.put("bottomLeftLatitude",a14.trim());
        map.put("bottomLeftLongitude",a15.trim());
        map.put("metadataSavePath",a16.trim());
        map.put("imageSavePath",a17.trim());
        map.put("uploadDate",a18.trim());
        try {
            return ((RsImage) Utils.mapToBean(map,RsImage.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
