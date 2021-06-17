package com.quizme.Model;


import javax.validation.constraints.NotBlank;

public class LoginObj  {


    @NotBlank(message = "password is mandatory")
    String password;

    @NotBlank(message = "userName is mandatory")
    String userName;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
