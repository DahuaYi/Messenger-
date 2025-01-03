package com.dahua.messaging.dto;


import java.util.Date;

import com.dahua.messaging.enumeration.Gender;

//This User information class will be store just a while and will be push into database
public class UserDTO { // DTO = data transfer object
    private String username; // alphanumeric
    private String password;
    private String email; // 2FA = two-factor authentication
    private String nickname; // utf-8: emoji...
    private String address;
    private Gender gender;
    private Date registerTime;
    private Boolean isValid;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
