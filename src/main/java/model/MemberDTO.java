package model;

public class MemberDTO {
    private String fullName;
    private String id;
    private String Category;
    private String email;
    private String phoneNumber;
    private String address;
    private String status;
    private Integer categoryId;

    public MemberDTO() {

    }

    public MemberDTO(String fullName, String id, String category, String email, String phoneNumber, String address,String status, Integer categoryId) {
        this.fullName = fullName;
        this.id = id;
        this.Category = category;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.categoryId = categoryId;

    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return Category;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
}
