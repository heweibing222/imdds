package edu.uestc.imdds.entitiy;

public class RsSubImage {
    private Integer id ;
    private Integer fatherId ;
    private String imageName;
    private String imageSavePath;

    @Override
    public String toString() {
        return "SubImage{" +
                "id='" + id + '\'' +
                ", fatherId='" + fatherId + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imageSavePath='" + imageSavePath + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageSavePath() {
        return imageSavePath;
    }

    public void setImageSavePath(String imageSavePath) {
        this.imageSavePath = imageSavePath;
    }

}
