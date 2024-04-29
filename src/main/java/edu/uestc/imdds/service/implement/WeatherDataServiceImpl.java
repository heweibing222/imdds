package edu.uestc.imdds.service.implement;

import edu.uestc.imdds.Utils.Utils;
import edu.uestc.imdds.entitiy.WeatherData;
import edu.uestc.imdds.mapper.WeatherDataMapper;
import edu.uestc.imdds.service.HdfsService;
import edu.uestc.imdds.service.WeatherDataService;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    @Value("${hdfs.path.weatherData}")
    private String weatherData_upload_path;

    @Autowired
    FileSystem fileSystem;


    @Autowired
    private WeatherDataMapper weatherDataMapper;
    @Autowired
    HdfsService hdfsService;

    @Override
    public WeatherData getById(Integer id) {
        return weatherDataMapper.getById(id);

    }

    @Override
    public WeatherData getByName(String dataName) {
        return weatherDataMapper.getByName(dataName);
    }

    @Override
    public Boolean addWeatherData(WeatherData weatherData, MultipartFile dataFile) {

        weatherData.setUploadDate(Utils.getNowTime());

        String filename = dataFile.getOriginalFilename();
        weatherData.setDataName(filename);

        String uploadDir = weatherData_upload_path+"/"+weatherData.getDataProperty()+"/";

        //文件上传到HDFS
        if(!hdfsService.uploadFile(dataFile,uploadDir)){return false;}

        weatherData.setImageSavePath(uploadDir + filename);//为什么先设置了数据路径值在后面if中再设置时不会被覆盖？

        if (!dataFile.isEmpty()) {
            String filetype = filename.substring(filename.lastIndexOf(".") + 1);
            weatherData.setDataFormat(filetype);
        }

        //元数据文件上传到MySQL
        if (weatherDataMapper.addWeatherData(weatherData) == 0) {return false;}

        return true;
    }

    @Override
    public Boolean deleteById(Integer id) {
        WeatherData weatherData = weatherDataMapper.getById(id);

        //删除HDFS-实体数据
        if(!hdfsService.deleteFile(weatherData.getImageSavePath())){
            return false;
        };
        //删除MySQL-元数据
        if (weatherDataMapper.deleteById(id) == 0){
            return false;
        };
        return true;
    }

    @Override
    public Boolean updateWeatherData(WeatherData weatherData) {
        if(getById(weatherData.getId()) == null){
            return false;
        }
        weatherData.setUploadDate(Utils.getNowTime());
        //System.out.println(weatherData.toString());
        int flag = weatherDataMapper.updateWeatherData(weatherData);
        return flag == 1;
    }

    @Override
    public List<WeatherData> getAll() {
        return  weatherDataMapper.getAll();
    }

    @Override
    public void download(Integer id, HttpServletResponse response) throws IOException {
        WeatherData weatherData = weatherDataMapper.getById(id);
        // 读到流中
        InputStream inputStream = fileSystem.open(new Path(weatherData.getImageSavePath()));
        response.reset();
        // 从文件路径中获取文件名
//        File file = new File(weatherData.getImageSavePath());
//        String fileName = file.getName();
        /* 允许跨域的主机地址 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(weatherData.getDataName(), "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[10240];
        int len;
        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
        while ((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();
    }

}
