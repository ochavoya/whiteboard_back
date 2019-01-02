package com.ochavoya.whiteboard.entities;


import com.ochavoya.whiteboard.dto.UserRegisterDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.ochavoya.whiteboard.utils.Utils.encrypt;

@Entity
@Table(name = "user")
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String username;
    @Column
    private String password;
    private String token;
    @Column
    private Boolean active;

    public UserEntity(UserRegisterDTO userRegisterDTO) {
        this.name = userRegisterDTO.getName();
        this.username = userRegisterDTO.getUsername();
        this.password = encrypt(userRegisterDTO.getPassword());
        this.active = true;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}