package com.ochavoya.whiteboard.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
public class UserRegisterDTO {
    @NotNull
    @Length(min=1, max=64)
    private String name;
    @NotNull
    @Length(min=3, max=16)
    private String username;
    @NotNull
    @Length(min=7)
    private String password;

    public UserRegisterDTO() { }

    public UserRegisterDTO(@NotNull @Length(min = 1, max = 64) String name, @NotNull @Length(min = 3, max = 16) String username, @NotNull @Length(min = 7) String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
