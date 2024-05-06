package edu.uestc.imdds;

import edu.uestc.imdds.service.implement.GeosolutionsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;

@SpringBootTest
class ImddsApplicationTests {
    @Autowired
    GeosolutionsServiceImpl geosolutionsService;


    @Test
    void contextLoads() {
    }

    @Test
    public void geoserver() throws FileNotFoundException {
        geosolutionsService.publishGeoTIFF(13);
       // geosolutionsService.createWorkspace("tiger");

    }

}
