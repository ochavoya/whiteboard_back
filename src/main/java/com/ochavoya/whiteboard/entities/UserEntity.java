package com.ochavoya.whiteboard.entities;


import com.ochavoya.whiteboard.dto.UserRegisterDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ochavoya.whiteboard.utils.Utils.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
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
    @Column
    private String token;
    @Column
    private Boolean active;

    public UserEntity(UserRegisterDTO userRegisterDTO) {
        this.name = userRegisterDTO.getName();
        this.username = userRegisterDTO.getUsername();
        this.password = encrypt(userRegisterDTO.getPassword());
        this.active = true;
    }
}

