package edu.uestc.imdds.config;

import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Configuration
public class GeoServerConfig {
    @Value("${geoserver.url}")
    private  String url;
    @Value("${geoserver.username}")
    private  String username;
    @Value("${geoserver.password}")
    private  String password;

    @Bean
    public GeoServerRESTManager getGeoServerRESTManager() throws MalformedURLException {
        return  new GeoServerRESTManager(new URL(url), username, password);
    }

}
