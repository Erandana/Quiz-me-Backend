package com.quizme.Model;

import javax.validation.constraints.NotBlank;

public class Token {

    @NotBlank(message = "jwt is mandatory")
    public String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
