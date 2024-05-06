package edu.uestc.imdds.service.implement;


import edu.uestc.imdds.entitiy.RsSubImage;
import edu.uestc.imdds.service.GeosolutionsService;
import edu.uestc.imdds.service.RsSubImageService;
import edu.uestc.imdds.service.WeatherDataService;
import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;


@Service
public class GeosolutionsServiceImpl implements GeosolutionsService {

    @Value("${geoserver.workspace.geotiff}")
    private String workspace_geotiff;
    @Value("${geoserver.workspace.netcdf}")
    private String workspace_netcdf;
    @Value("${local.file.path}")
    private String localFilePath;

    @Autowired
    GeoServerRESTManager geoServerRESTManager;
    @Autowired
    RsSubImageService rsSubImageService;
    @Autowired
    WeatherDataService weatherDataService;
    @Autowired
    private FileSystem fileSystem;

    @Override
    public boolean publishGeoTIFF(Integer id) {
        createWorkspace(workspace_geotiff);
        RsSubImage rsSubImage = rsSubImageService.getById(id);
        String storename = rsSubImage.getImageName();
        String hdfsPath = rsSubImage.getImageSavePath();
        File localFile = new File(localFilePath+storename);
        // 从 HDFS 中读取文件，并写入到本地文件中
        try (OutputStream out = new FileOutputStream(localFile)) {
            // 将 HDFS 中的文件复制到本地文件
            fileSystem.copyToLocalFile(new Path(hdfsPath), new Path(localFilePath));
            System.out.println("File copied from HDFS to local filesystem.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = localFilePath+storename;
        boolean isPublish = false;
        try {
            isPublish = geoServerRESTManager.getPublisher().publishGeoTIFF(workspace_geotiff, storename,new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return isPublish;
    }

    /**
     * 创建工作区
     * @param workSpaceName
     */
    @Override
    public boolean createWorkspace(String workSpaceName) {
        GeoServerRESTPublisher publisher = geoServerRESTManager.getPublisher();
        GeoServerRESTReader reader = geoServerRESTManager.getReader();
        List<String> workspaces = reader.getWorkspaceNames();
        if (!workspaces.contains(workSpaceName)) {
            boolean flag = publisher.createWorkspace(workSpaceName);
            System.out.println("create 工作空间: " + flag);
            return flag;
        } else {
            System.out.println("工作空间已经存在了,workspace:" + workSpaceName);
        }
        return false;
    }

    @Override
    public boolean setStyle(String workspace, String coverageName, String style) {
        return geoServerRESTManager.getPublisher().setStyle(workspace, coverageName, style);
    }

}
