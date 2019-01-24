package com.ochavoya.whiteboard.dto;

import com.ochavoya.whiteboard.entities.WhiteboardItemEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class WhiteboardItemDTO {
    @NotNull
    private Integer boardId;
    @NotNull
    private Integer sectionId;
    @NotNull
    @Length( min = 1, max=64)
    private String title;
    @NotNull
    @Length( max=200)
    private String detail;
    @NotNull
    private Timestamp expiresOn;
    @NotNull
    private String token;

    public WhiteboardItemDTO(){}

    public WhiteboardItemDTO(Integer boardId, Integer sectionId, String title, String detail, Timestamp expiresOn, String token) {
        this.boardId = boardId;
        this.sectionId = sectionId;
        this.title = title;
        this.detail = detail;
        this.expiresOn = expiresOn;
        this.token  = token;
    }

    public WhiteboardItemDTO(WhiteboardItemEntity whiteboardItemEntity) {
        this.boardId = whiteboardItemEntity.getBoardId();
        this.sectionId = whiteboardItemEntity.getSectionId();
        this.title = whiteboardItemEntity.getTitle();
        this.detail = whiteboardItemEntity.getDetail();
    }

    public Integer getBoardId() {
        return boardId;
    }

    public Integer getSectionId() {
        return sectionId;
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

    public String getToken() {
        return token;
    }
}
