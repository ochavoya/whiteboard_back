package com.ochavoya.whiteboard.entities;


import com.ochavoya.whiteboard.dto.WhiteboardItemDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="items")
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
    @Column
    private Integer userId;
    @Column
    private String title;
    @Column
    private String detail;
    @Column
    private Timestamp expiresOn;
    @Column
    private Boolean active;

    public WhiteboardItemEntity(WhiteboardItemDTO whiteboardItemDTO, Integer userId) {
        this.boardId = whiteboardItemDTO.getBoardId();
        this.sectionId = whiteboardItemDTO.getSectionId();
        this.userId = userId;
        this.title = whiteboardItemDTO.getTitle();
        this.detail = whiteboardItemDTO.getDetail();
        this.expiresOn = whiteboardItemDTO.getExpiresOn();
        this.active = true;
    }

    public Integer getId() {
        return id;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public Timestamp getExpiresOn() {
        return expiresOn;
    }

    public Boolean getActive() {
        return active;
    }
}