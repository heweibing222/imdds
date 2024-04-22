package edu.uestc.imdds.entitiy;

public class RsImage {
    private Integer id ;
    private String imageName;
    private String satellite;
    private String sensor;
    private String shootDate;
    private String shootTime ;
    private String topLeftLatitude ;
    private String topLeftLongitude ;
    private String topRightLatitude ;
    private String topRightLongitude ;
    private String bottomRightLatitude ;
    private String bottomRightLongitude ;
    private String bottomLeftLatitude ;
    private String bottomLeftLongitude ;
    private String imageSavePath;
    private String metadataSavePath;
    private String uploadDate;

    public RsImage() {
    }

    public RsImage(Integer id, String imageName, String satellite, String sensor, String shootDate, String shootTime, String topLeftLatitude, String topLeftLongitude, String topRightLatitude, String topRightLongitude, String bottomRightLatitude, String bottomRightLongitude, String bottomLeftLatitude, String bottomLeftLongitude, String imageSavePath, String metadataSavePath, String uploadDate) {
        this.id = id;
        this.imageName = imageName;
        this.satellite = satellite;
        this.sensor = sensor;
        this.shootDate = shootDate;
        this.shootTime = shootTime;
        this.topLeftLatitude = topLeftLatitude;
        this.topLeftLongitude = topLeftLongitude;
        this.topRightLatitude = topRightLatitude;
        this.topRightLongitude = topRightLongitude;
        this.bottomRightLatitude = bottomRightLatitude;
        this.bottomRightLongitude = bottomRightLongitude;
        this.bottomLeftLatitude = bottomLeftLatitude;
        this.bottomLeftLongitude = bottomLeftLongitude;
        this.imageSavePath = imageSavePath;
        this.metadataSavePath = metadataSavePath;
        this.uploadDate = uploadDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getShootDate() {
        return shootDate;
    }

    public void setShootDate(String shootDate) {
        this.shootDate = shootDate;
    }

    public String getShootTime() {
        return shootTime;
    }

    public void setShootTime(String shootTime) {
        this.shootTime = shootTime;
    }

    public String getTopLeftLatitude() {
        return topLeftLatitude;
    }

    public void setTopLeftLatitude(String topLeftLatitude) {
        this.topLeftLatitude = topLeftLatitude;
    }

    public String getTopLeftLongitude() {
        return topLeftLongitude;
    }

    public void setTopLeftLongitude(String topLeftLongitude) {
        this.topLeftLongitude = topLeftLongitude;
    }

    public String getTopRightLatitude() {
        return topRightLatitude;
    }

    public void setTopRightLatitude(String topRightLatitude) {
        this.topRightLatitude = topRightLatitude;
    }

    public String getTopRightLongitude() {
        return topRightLongitude;
    }

    public void setTopRightLongitude(String topRightLongitude) {
        this.topRightLongitude = topRightLongitude;
    }

    public String getBottomRightLatitude() {
        return bottomRightLatitude;
    }

    public void setBottomRightLatitude(String bottomRightLatitude) {
        this.bottomRightLatitude = bottomRightLatitude;
    }

    public String getBottomRightLongitude() {
        return bottomRightLongitude;
    }

    public void setBottomRightLongitude(String bottomRightLongitude) {
        this.bottomRightLongitude = bottomRightLongitude;
    }

    public String getBottomLeftLatitude() {
        return bottomLeftLatitude;
    }

    public void setBottomLeftLatitude(String bottomLeftLatitude) {
        this.bottomLeftLatitude = bottomLeftLatitude;
    }

    public String getBottomLeftLongitude() {
        return bottomLeftLongitude;
    }

    public void setBottomLeftLongitude(String bottomLeftLongitude) {
        this.bottomLeftLongitude = bottomLeftLongitude;
    }

    public String getImageSavePath() {
        return imageSavePath;
    }

    public void setImageSavePath(String imageSavePath) {
        this.imageSavePath = imageSavePath;
    }

    public String getMetadataSavePath() {
        return metadataSavePath;
    }

    public void setMetadataSavePath(String metadataSavePath) {
        this.metadataSavePath = metadataSavePath;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}
