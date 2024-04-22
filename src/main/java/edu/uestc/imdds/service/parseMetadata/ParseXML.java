package edu.uestc.imdds.service.parseMetadata;

import edu.uestc.imdds.Utils.Utils;import edu.uestc.imdds.entitiy.RsImage;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class ParseXML {
    @Autowired
    private FileSystem fileSystem;
    @Value("${hdfs.path.rsImage.imageData}")
    private String imageData_upload_path;

    public RsImage parse(String filePath, String satelliteType){
        SAXReader reader = new SAXReader();
        //存储元数据
        Map<String, Object> resultAttributes = new HashMap<String, Object>();
        String imageName="unknow";
        try {
            FSDataInputStream fsDataInputStream = fileSystem.open(new Path(filePath));
            Document document = reader.read(fsDataInputStream);
            Element productMetaData = document.getRootElement();
            //将文件名称从元数据文件名称中解析出来
            //例如：/mmids/metadata/2022/GAOFEN/GF2_PMS1_E103.5_N31.0_20150717_L2A0001962343-PAN1.xml
            //解析得到：L2A0001962343
            imageName = Utils.removeExtension(String.valueOf(filePath.split("/")[4].split("_")[5]));
            resultAttributes.put("imageName", imageName);
            resultAttributes.put("id", Utils.getTimeMillis());
            resultAttributes.put("metadataSavePath", filePath);
            resultAttributes.put("uploadDate", Utils.getNowDate());
            resultAttributes.put("imageSavePath", imageData_upload_path+"/"+satelliteType+"/"+imageName);
            Iterator storeit = productMetaData.elementIterator();
            //遍历xml文件标签，获得元数据
            while(storeit.hasNext()){
                Element metaElement = (Element) storeit.next();
                String nodeName = metaElement.getName();
                if(nodeName.equals("SatelliteID")){
                    String name = metaElement.getStringValue();
                    resultAttributes.put("satellite", name);
                }else if(nodeName.equals("SensorID")){
                    String name = metaElement.getStringValue();
                    resultAttributes.put("sensor", name);
                }else if(nodeName.equals("ReceiveTime")){
                    String name = metaElement.getStringValue();
                    resultAttributes.put("shootDate", name.split(" ")[0]);
                    resultAttributes.put("shootTime", name.split(" ")[1]);
                }else if(nodeName.equals("TopLeftLatitude")){
                    String name = metaElement.getStringValue();
                    resultAttributes.put("topLeftLatitude", name);
                }else if(nodeName.equals("TopLeftLongitude")){
                    String name = metaElement.getStringValue();
                    resultAttributes.put("topLeftLongitude", name);
                }else if(nodeName.equals("TopRightLatitude")){
                    String name = metaElement.getStringValue();
                    resultAttributes.put("topRightLatitude", name);
                }else if(nodeName.equals("TopRightLongitude")){
                    String name = metaElement.getStringValue();
                    resultAttributes.put("topRightLongitude", name);
                }else if(nodeName.equals("BottomRightLatitude")){
                    String name = metaElement.getStringValue();
                    resultAttributes.put("bottomRightLatitude", name);
                }else if(nodeName.equals("BottomRightLongitude")){
                    String name = metaElement.getStringValue();
                    resultAttributes.put("bottomRightLongitude",name);
                }else if(nodeName.equals("BottomLeftLatitude")){
                    String name = metaElement.getStringValue();
                    resultAttributes.put("bottomLeftLatitude", name);
                }else if(nodeName.equals("BottomLeftLongitude")){
                    String name = metaElement.getStringValue();
                    resultAttributes.put("bottomLeftLongitude", name);
                }
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        try {
            return ((RsImage) Utils.mapToBean(resultAttributes,RsImage.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
