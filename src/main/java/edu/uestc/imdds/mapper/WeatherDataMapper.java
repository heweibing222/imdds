package edu.uestc.imdds.mapper;

import edu.uestc.imdds.entitiy.WeatherData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WeatherDataMapper {
    WeatherData getById(Integer id);
    WeatherData getByName(String Name);
    int addWeatherData(WeatherData weatherData);
    int deleteById(Integer id);
    int updateWeatherData(WeatherData weatherData);
    List<WeatherData> getAll();


}
