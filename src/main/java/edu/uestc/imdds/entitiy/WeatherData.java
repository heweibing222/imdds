package edu.uestc.imdds.entitiy;

public class WeatherData {
    private Integer id ;
    private String dataName;
    private String dataProperty; //数据属性
    private String dataFormat; //数据格式
    private String location;  //位置
    private String beginDate; //时间
    private String endDate;
    private String resolution; // 分辨率
    private String imageSavePath;
    private String uploadDate;
    // GeoServer
    //...


    public WeatherData() {
    }

    public WeatherData(Integer id, String dataName, String dataProperty, String dataFormat, String location, String beginDate, String endDate, String resolution, String imageSavePath, String uploadDate) {
        this.id = id;
        this.dataName = dataName;
        this.dataProperty = dataProperty;
        this.dataFormat = dataFormat;
        this.location = location;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.resolution = resolution;
        this.imageSavePath = imageSavePath;
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "id=" + id +
                ", dataName='" + dataName + '\'' +
                ", dataProperty='" + dataProperty + '\'' +
                ", dataFormat='" + dataFormat + '\'' +
                ", location='" + location + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", resolution='" + resolution + '\'' +
                ", imageSavePath='" + imageSavePath + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                '}';
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

    public String getDataProperty() {
        return dataProperty;
    }

    public void setDataProperty(String dataProperty) {
        this.dataProperty = dataProperty;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
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
