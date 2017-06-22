package com.example.lttha.a14110180_lethithao_foody.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lttha on 5/17/2017.
 */

public class User {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("dateparticipation")
    @Expose
    private String dateparticipation;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("img")
    @Expose
    private String img;

    public User(Integer id, String name, String lastname, String mail, String username, String pass, String dateparticipation, String sex, String birthday, String img) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.username = username;
        this.pass = pass;
        this.dateparticipation = dateparticipation;
        this.sex = sex;
        this.birthday = birthday;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDateparticipation() {
        return dateparticipation;
    }

    public void setDateparticipation(String dateparticipation) {
        this.dateparticipation = dateparticipation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
