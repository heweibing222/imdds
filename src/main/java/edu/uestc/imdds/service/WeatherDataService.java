package edu.uestc.imdds.service;


import edu.uestc.imdds.entitiy.WeatherData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface WeatherDataService {
    WeatherData getById(Integer id);
    WeatherData getByName(String dataName);
    Boolean addWeatherData(WeatherData weatherData, MultipartFile metadataFile);
    Boolean deleteById(Integer id);
    Boolean updateWeatherData(WeatherData weatherData);
    List<WeatherData> getAll();
    void download(Integer id, HttpServletResponse response) throws IOException;
}
