package edu.vio.violympic.model;

import java.io.Serializable;

public class UserFr implements Serializable {
    private String email;
    private String password;
    private String fullName;
    private String username;
    private String phone;

    public UserFr(String email, String password, String fullName, String username, String phone) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.username = username;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
