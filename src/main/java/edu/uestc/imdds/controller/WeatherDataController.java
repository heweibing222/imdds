package edu.uestc.imdds.controller;

import edu.uestc.imdds.entitiy.WeatherData;
import edu.uestc.imdds.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static edu.uestc.imdds.Utils.Utils.page;

@CrossOrigin(origins = {"http://localhost:5173", "null"})
@RestController
@RequestMapping("/weatherData")

public class WeatherDataController {
    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping
    @ResponseBody
    public Result getById(Integer id){
        WeatherData weatherData =  weatherDataService.getById(id);
        return new Result(weatherData!=null ? Code.GET_OK:Code.GET_ERR, weatherData, weatherData!=null ? "气象数据查询成功！":"未查找到相关气象数据！");
    }

    @GetMapping("/name")
    @ResponseBody
    public Result getByName(String dataName){
        WeatherData weatherData =  weatherDataService.getByName(dataName);
        return new Result(weatherData!=null ? Code.GET_OK:Code.GET_ERR, weatherData, weatherData!=null ? "气象数据查询成功！":"相关气象数据名不存在！");
    }

    @PostMapping("/addWeatherData")
    @ResponseBody
    //@RequestBody注解用于将HTTP请求的请求体中的JSON数据转换为WeatherData对象
    public Result addWeatherData(@RequestPart("data") WeatherData weatherData, @RequestPart("file") MultipartFile dataFile) {
        System.out.println(weatherData.toString());
        boolean flag = weatherDataService.addWeatherData(weatherData, dataFile);
        return new Result(flag ? Code.ADD_OK:Code.ADD_ERR, flag, flag ? "气象数据添加成功！":"气象数据添加失败，数据格式可能有误！");
    }

    @DeleteMapping
    @ResponseBody
    public Result deleteById(Integer id){
        boolean flag = weatherDataService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR, flag, flag ? "气象数据删除成功！":"删除失败，气象数据可能不存在！");
    }

    @PutMapping
    @ResponseBody
    public Result updateWeatherData(@RequestBody WeatherData weatherData) {
        boolean flag = weatherDataService.updateWeatherData(weatherData);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR, flag, flag ? "气象数据信息修改成功！":"修改失败，气象数据可能不存在！");
    }

    @GetMapping("/all")
    @ResponseBody
    public Result getAll(String pageIndex,String pageSize){
        List<WeatherData> weatherData =  weatherDataService.getAll();
        weatherData = page(weatherData,Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return new Result(!weatherData.isEmpty() ? Code.GET_OK:Code.GET_ERR,!weatherData.isEmpty() ? weatherData:null, !weatherData.isEmpty() ? "气象数据查询成功！":"未查找到任何气象数据！");
    }

    @GetMapping ("/download")
    @ResponseBody
    public void download(Integer id, HttpServletResponse response) throws IOException {
        weatherDataService.download(id,response);
    }

}
