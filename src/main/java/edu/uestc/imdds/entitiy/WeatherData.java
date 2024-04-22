package edu.uestc.imdds.entitiy;

public class WeatherData {
    private Integer id ;
    private String dataName;
    private String dateProperty; //数据属性
    private String dataFormat; //数据格式
    private String location;  //位置
    private String date; //时间
    private String resulation; // 分辨率
    private String imageSavePath;
    private String uploadDate;
    // GeoServer
    //...

    public WeatherData() {
    }

    public WeatherData(Integer id, String dataName, String dateProperty, String dataFormat, String location, String date, String resulation, String imageSavePath, String uploadDate) {
        this.id = id;
        this.dataName = dataName;
        this.dateProperty = dateProperty;
        this.dataFormat = dataFormat;
        this.location = location;
        this.date = date;
        this.resulation = resulation;
        this.imageSavePath = imageSavePath;
        this.uploadDate = uploadDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDateProperty() {
        return dateProperty;
    }

    public void setDateProperty(String dateProperty) {
        this.dateProperty = dateProperty;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResulation() {
        return resulation;
    }

    public void setResulation(String resulation) {
        this.resulation = resulation;
    }

    public String getImageSavePath() {
        return imageSavePath;
    }

    public void setImageSavePath(String imageSavePath) {
        this.imageSavePath = imageSavePath;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}
