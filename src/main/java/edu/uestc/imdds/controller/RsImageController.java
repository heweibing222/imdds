package edu.uestc.imdds.controller;


import edu.uestc.imdds.entitiy.RsImage;
import edu.uestc.imdds.entitiy.RsSubImage;
import edu.uestc.imdds.service.implement.RsImageServiceImpl;
import edu.uestc.imdds.service.implement.RsSubImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static edu.uestc.imdds.Utils.Utils.page;

@CrossOrigin(origins = {"http://localhost:5173", "null"})
@RestController
@RequestMapping("/rsImage")
public class RsImageController {
    @Autowired
    private RsImageServiceImpl rsImageService ;
    @Autowired
    private RsSubImageServiceImpl rsSubImageService ;

    @GetMapping
    @ResponseBody
    public Result getById(Integer id){
        RsImage rsImage =  rsImageService.getById(id);
        return new Result(rsImage!=null ? Code.GET_OK:Code.GET_ERR, rsImage, rsImage!=null ? "遥感影像查询成功！":"未查找到相关遥感影像！");
    }

    @PostMapping("/addMetadata")
    @ResponseBody
    public Result addMetadata(@RequestBody RsImage rsImage) {
        boolean flag = rsImageService.addMetadata(rsImage);
        return new Result(flag ? Code.ADD_OK:Code.ADD_ERR, flag, flag ? "遥感影像元数据添加成功！":"遥感影像元数据添加失败，可能已存在相同名称的遥感影像！");
    }

    @PostMapping
    @ResponseBody
    public Result addRsImage(MultipartFile metadataFile, String satelliteType, MultipartFile[] imageFiles){
        boolean flag = rsImageService.addRsImage(metadataFile,satelliteType,imageFiles);
        return  new Result(flag ? Code.UPLOAD_OK:Code.UPLOAD_ERR,flag,flag?"遥感影像添加成功！":"遥感影像数据失败！");
    }

    @DeleteMapping
    @ResponseBody
    public Result deleteById(Integer id){
        boolean flag = rsImageService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR, flag, flag ? "遥感影像删除成功！":"遥感影像删除失败！");
    }

    @PutMapping
    @ResponseBody
    public Result updateRsImage(@RequestBody RsImage rsImage) {
        boolean flag = rsImageService.updateRsImage(rsImage);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR, flag, flag ? "遥感影像信息修改成功！":"遥感影像信息修改失败！");
    }

    @GetMapping("/all")
    @ResponseBody
    public Result getAll(String pageIndex,String pageSize){
        List<RsImage> rsImages =  rsImageService.getAll();
        rsImages = page(rsImages,Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return new Result(!rsImages.isEmpty() ? Code.GET_OK:Code.GET_ERR,!rsImages.isEmpty() ? rsImages:null, !rsImages.isEmpty() ? "遥感影像查询成功！":"未查找到遥感影像！");
    }

    @GetMapping("/getList")
    @ResponseBody
    public Result getByFatherId(Integer id){
        List<RsSubImage> imageList = rsSubImageService.getByFatherId(id);
        Integer code = !imageList.isEmpty() ? Code.GET_OK : Code.GET_ERR;
        String msg =  !imageList.isEmpty() ? "查询成功！" : "暂未找到可以下载的影像数据！";
        return new Result(code,imageList,msg);
    }

    @GetMapping ("/download")
    @ResponseBody
    public void download(Integer id, HttpServletResponse response) throws IOException {
        rsSubImageService.download(id,response);
    }

    @GetMapping("/publish")
    @ResponseBody
    public Result publish(Integer id){
        boolean flag = rsSubImageService.publish(id);
        System.out.println("flag:"+flag);
        return new Result(flag ? Code.GET_OK:Code.GET_ERR, flag, flag ? "遥感影像发布成功！":"遥感影像发布失败！");
    }
}
