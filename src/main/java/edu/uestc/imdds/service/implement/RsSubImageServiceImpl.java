package edu.uestc.imdds.service.implement;

import edu.uestc.imdds.entitiy.RsSubImage;
import edu.uestc.imdds.mapper.RsSubImageMapper;
import edu.uestc.imdds.service.RsSubImageService;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Service
public class RsSubImageServiceImpl implements RsSubImageService {
    @Autowired
    GeosolutionsServiceImpl geosolutionService;
    @Autowired
    FileSystem fileSystem;
    @Autowired
    RsSubImageMapper rsSubImageMapper;

    @Override
    public List<RsSubImage> getAll() {
        return rsSubImageMapper.getAll();
    }

    @Override
    public List<RsSubImage> getByFatherId(Integer fatherId) {
        return rsSubImageMapper.getByFatherId(fatherId);
    }

    @Override
    public RsSubImage getById(Integer id) {
        return rsSubImageMapper.getById(id);
    }


    @Override
    public boolean add(RsSubImage rsSubImage) {
        int flag = rsSubImageMapper.addRsImage(rsSubImage);
        return flag==1;
    }

    @Override
    public boolean deleteByFatherId(Integer fatherId) {
        List<RsSubImage> list = rsSubImageMapper.getByFatherId(fatherId);
        for (int i = 0; i < list.size(); i++) {
            RsSubImage rsSubImage = list.get(i);
            if(rsSubImageMapper.deleteById(rsSubImage.getId())==0){return false;}
        }
        return true;
    }

    @Override
    public void download(Integer id, HttpServletResponse response) throws IOException {
        RsSubImage rsSubImage = rsSubImageMapper.getById(id);
        // 读到流中
        InputStream inputStream = fileSystem.open(new Path(rsSubImage.getImageSavePath()));
//        InputStreamResource resource = new InputStreamResource(inputStream);
        response.reset();
        /* 允许跨域的主机地址 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(rsSubImage.getImageName(), "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[10240];
        int len;
        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
        while ((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();
    }

    @Override
    public boolean publish(Integer id) {
        RsSubImage subImage = rsSubImageMapper.getById(id);
        return geosolutionService.publishGeoTIFF(subImage);
    }


}
