package com.learning.seckill.pojo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long phone;
    private String nickname;
    private String password;
    private String avatar;
    private Date registerDate;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "phone=" + phone +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }
}
