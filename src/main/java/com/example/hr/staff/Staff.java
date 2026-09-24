package com.example.hr.staff;

/** 员工档案：本练习只保留姓名、性别、生日和手机号四项业务信息。 */
public class Staff {
    private int id;
    private String name = "";
    private String gender = "男";
    private String birthday = "";
    private String phone = "";

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getInitial() { return name == null || name.isEmpty() ? "?" : name.substring(0, 1); }
}
