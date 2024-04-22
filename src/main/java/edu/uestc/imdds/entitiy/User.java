package edu.uestc.imdds.entitiy;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String gender; //1=>male 2=>female
    private String phoneNumber;
    private String email;
    private String education;
    private String nationality;
    private String address;
    private String organization;
    private Integer loginTimes;
    private String authority; //1=>管理员 2=>普通用户

    public User(){}

    public User(Integer id, String username, String password, String gender, String phoneNumber, String email, String education, String nationality, String address, String organization, Integer loginTimes, String authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.education = education;
        this.nationality = nationality;
        this.address = address;
        this.organization = organization;
        this.loginTimes = loginTimes;
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", education='" + education + '\'' +
                ", nationality='" + nationality + '\'' +
                ", address='" + address + '\'' +
                ", organization='" + organization + '\'' +
                ", loginTimes=" + loginTimes +
                ", authority=" + authority +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
