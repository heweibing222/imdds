package edu.uestc.imdds;

import edu.uestc.imdds.service.implement.GeosolutionsServiceImpl;
import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;

@SpringBootTest
class ImddsApplicationTests {
    @Autowired
    GeosolutionsServiceImpl geosolutionsService;
    @Autowired
    GeoServerRESTManager geoServerRESTManager;
    @Value("${geoserver.workspace.geotiff}")
    private String workspace_geotiff;

    @Test
    void contextLoads() {
    }

    @Test
    public void geoserver() throws FileNotFoundException {
//        geosolutionsService.publishGeoTIFF(13);
       // geosolutionsService.createWorkspace("tiger");
        String layerName = "LT51300442011320BKT00_B3.TIF";
//        boolean flag = geoServerRESTManager.getReader().existsLayer(workspace_geotiff, layerName);
        boolean flag = geoServerRESTManager.getReader().existsCoverage(workspace_geotiff, "LT51300442011320BKT00_B1.TIF",layerName);
        System.out.println(flag);
    }

}
