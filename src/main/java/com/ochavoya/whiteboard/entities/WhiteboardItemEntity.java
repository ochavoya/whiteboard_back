package com.ochavoya.whiteboard.entities;


import com.ochavoya.whiteboard.dto.WhiteboardItemDTO;
import lombok.Data;
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
    private Integer itemId;
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

    public WhiteboardItemEntity(){}

    public WhiteboardItemEntity(WhiteboardItemDTO whiteboardItemDTO, Integer userId) {
        this.boardId = whiteboardItemDTO.getBoardId();
        this.sectionId = whiteboardItemDTO.getSectionId();
        this.userId = userId;
        this.title = whiteboardItemDTO.getTitle();
        this.detail = whiteboardItemDTO.getDetail();
        this.expiresOn = whiteboardItemDTO.getExpiresOn();
        this.active = true;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Timestamp getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Timestamp expiresOn) {
        this.expiresOn = expiresOn;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}