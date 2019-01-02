package com.ochavoya.whiteboard.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="items")
@Data
@NoArgsConstructor
public class WhiteboardItemEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer boardId;
    @Column
    private Integer sectionId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private UserEntity user;
    @Column
    private String title;
    @Column
    private String detail;
    @Column
    private Timestamp createdOn;
    @Column
    private Timestamp expiresOn;
    @Column
    private Boolean active;
}