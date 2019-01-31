package com.ochavoya.whiteboard.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserLoginDTO {
    @NotNull
    @Length(min=1, max=16)
    private String username;
    @NotNull
    @Length(min=7)
    private String password;

    public UserLoginDTO(){}

    public UserLoginDTO(@NotNull @Length(min = 1, max = 16) String username, @NotNull @Length(min = 7) String password) {
        this.username = username;
        this.password = password;
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
}
