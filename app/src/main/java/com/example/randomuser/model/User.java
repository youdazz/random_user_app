package com.example.randomuser.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    UserName name;
    String phone;
    String cell;
    String mail;
    Date dateOfBirth;
    Integer age;
    UserPicture picture;
    Location location;

    public User(UserApiModel userApiModel){
        this.name = userApiModel.name;
        this.phone = userApiModel.phone;
        this.cell = userApiModel.cell;
        this.mail = userApiModel.email;
        this.age = userApiModel.dob.age;
        this.dateOfBirth = userApiModel.dob.date;
        this.picture = userApiModel.picture;
        this.location = userApiModel.location;
    }

    public String getName(){
        return String.format("%s %s", name.first, name.last);
    }

    public String getPhone() {
        return phone;
    }

    public String getCell(){
        return cell;
    }

    public String getMail(){
        return mail;
    }

    public String getThumbnail(){
        return picture.thumbnail;
    }

    public String getLargePicture(){
        return picture.large;
    }

    public Integer getAge() {
        return age;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    public String getStreet() {
        return location.getStreet();
    }

    public String getCity() {
        return location.getCity();
    }

    public String getState() {
        return location.getState();
    }

    public String getCountry() {
        return location.getCountry();
    }
}
