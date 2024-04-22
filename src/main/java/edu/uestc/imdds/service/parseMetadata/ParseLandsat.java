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
public class ParseLandsat {

    @Autowired
    private HdfsService hdfsService;
    @Value("${hdfs.path.rsImage.imageData}")
    private String imageData_upload_path;
    public RsImage parse(String filePath, String satelliteType) {
        List<String> list = hdfsService.readTxtFile(filePath);
        //存放临时结果
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        //存放最终结果
        Map<String, Object> resultAttributes = new HashMap<String, Object>();
        //依次读取每一行，解析属性与相应的值
        for(int i=0 ;i< list.size();i++){
            String tmp = list.get(i);
            if(!tmp.equals("")){
                if(tmp.split(" = ").length==2) {
                    String attribute = tmp.split(" = ")[0].trim();
                    String value = tmp.split(" = ")[1];
                    value=value.replace("\"","").trim();
                    tmpMap.put(attribute,value);
                }
            }
        }
        //按照需要选取相应的属性
        String imageName ="Unkown";
        //总文件名
        if(tmpMap.get("LANDSAT_SCENE_ID")!=null){
            imageName= (String) tmpMap.get("LANDSAT_SCENE_ID");
            resultAttributes.put("imageName", imageName);
        }else if(tmpMap.get("LANDSAT_PRODUCT_ID")!=null){
            imageName= (String) tmpMap.get("LANDSAT_PRODUCT_ID");
            resultAttributes.put("imageName", imageName);
        }else{
            resultAttributes.put("imageName", imageName);
        }
        //卫星
        if(tmpMap.get("SPACECRAFT_ID")!=null){
            String tmp = (String) tmpMap.get("SPACECRAFT_ID");
            resultAttributes.put("satellite", tmp);
        }
        //传感器
        if(tmpMap.get("SENSOR_ID")!=null){
            String tmp = (String) tmpMap.get("SENSOR_ID");
            resultAttributes.put("sensor", tmp);
        }
        //时间
        if(tmpMap.get("SCENE_CENTER_TIME")!=null){
            String tmp = (String) tmpMap.get("SCENE_CENTER_TIME");
            resultAttributes.put("shootTime", tmp.replaceAll("\\..*$", ""));
        }else if(tmpMap.get("SCENE_CENTER_SCAN_TIME")!=null){
            String tmp = (String) tmpMap.get("SCENE_CENTER_SCAN_TIME");
            resultAttributes.put("shootTime", tmp.replaceAll("\\..*$", ""));
        }
        //日期
        if(tmpMap.get("DATE_ACQUIRED")!=null){
            String tmp = (String) tmpMap.get("DATE_ACQUIRED");
            resultAttributes.put("shootDate", tmp.replaceAll("\\..*$", ""));
        }else if(tmpMap.get("ACQUISITION_DATE")!=null){
            String tmp = (String) tmpMap.get("ACQUISITION_DATE");
            resultAttributes.put("shootDate", tmp.replaceAll("\\..*$", ""));
        }
        //四个经纬度坐标
        if(tmpMap.get("CORNER_UL_LAT_PRODUCT")!=null){
            String tmp = (String) tmpMap.get("CORNER_UL_LAT_PRODUCT");
            resultAttributes.put("topLeftLatitude", tmp);
        }else if(tmpMap.get("PRODUCT_UL_CORNER_LAT")!=null){
            String tmp = (String) tmpMap.get("PRODUCT_UL_CORNER_LAT");
            resultAttributes.put("topLeftLatitude", tmp);
        }

        if(tmpMap.get("CORNER_UL_LON_PRODUCT")!=null){
            String tmp = (String) tmpMap.get("CORNER_UL_LON_PRODUCT");
            resultAttributes.put("topLeftLongitude", tmp);
        }else if(tmpMap.get("PRODUCT_UL_CORNER_LON")!=null){
            String tmp = (String) tmpMap.get("PRODUCT_UL_CORNER_LON");
            resultAttributes.put("topLeftLongitude", tmp);
        }

        if(tmpMap.get("CORNER_UR_LAT_PRODUCT")!=null){
            String tmp = (String) tmpMap.get("CORNER_UR_LAT_PRODUCT");
            resultAttributes.put("topRightLatitude", tmp);
        }else if(tmpMap.get("PRODUCT_UR_CORNER_LAT")!=null){
            String tmp = (String) tmpMap.get("PRODUCT_UR_CORNER_LAT");
            resultAttributes.put("topRightLatitude", tmp);
        }

        if(tmpMap.get("CORNER_UR_LON_PRODUCT")!=null){
            String tmp = (String) tmpMap.get("CORNER_UR_LON_PRODUCT");
            resultAttributes.put("topRightLongitude", tmp);
        }else if(tmpMap.get("PRODUCT_UR_CORNER_LON")!=null){
            String tmp = (String) tmpMap.get("PRODUCT_UR_CORNER_LON");
            resultAttributes.put("topRightLongitude", tmp);
        }

        if(tmpMap.get("CORNER_LL_LAT_PRODUCT")!=null){
            String tmp = (String) tmpMap.get("CORNER_LL_LAT_PRODUCT");
            resultAttributes.put("bottomLeftLatitude", tmp);
        }else if(tmpMap.get("PRODUCT_LL_CORNER_LAT")!=null){
            String tmp = (String) tmpMap.get("PRODUCT_LL_CORNER_LAT");
            resultAttributes.put("bottomLeftLatitude", tmp);
        }

        if(tmpMap.get("CORNER_LL_LON_PRODUCT")!=null){
            String tmp = (String) tmpMap.get("CORNER_LL_LON_PRODUCT");
            resultAttributes.put("bottomLeftLongitude", tmp);
        }else if(tmpMap.get("PRODUCT_LL_CORNER_LON")!=null){
            String tmp = (String) tmpMap.get("PRODUCT_LL_CORNER_LON");
            resultAttributes.put("bottomLeftLongitude", tmp);
        }

        if(tmpMap.get("CORNER_LR_LAT_PRODUCT")!=null){
            String tmp = (String) tmpMap.get("CORNER_LR_LAT_PRODUCT");
            resultAttributes.put("bottomRightLatitude", tmp);
        }else if(tmpMap.get("PRODUCT_LR_CORNER_LAT")!=null){
            String tmp = (String) tmpMap.get("PRODUCT_LR_CORNER_LAT");
            resultAttributes.put("bottomRightLatitude", tmp);
        }

        if(tmpMap.get("CORNER_LR_LON_PRODUCT")!=null){
            String tmp = (String) tmpMap.get("CORNER_LR_LON_PRODUCT");
            resultAttributes.put("bottomRightLongitude", tmp);
        }else if(tmpMap.get("PRODUCT_LR_CORNER_LON")!=null){
            String tmp = (String) tmpMap.get("PRODUCT_LR_CORNER_LON");
            resultAttributes.put("bottomRightLongitude", tmp);
        }
//        resultAttributes.put("id", Utils.getTimeMillis());
        resultAttributes.put("metadataSavePath", filePath);
        resultAttributes.put("imageSavePath", imageData_upload_path+"/"+satelliteType+"/"+imageName);
        resultAttributes.put("uploadDate", Utils.getNowDate());
        try {
            return ((RsImage) Utils.mapToBean(resultAttributes,RsImage.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
