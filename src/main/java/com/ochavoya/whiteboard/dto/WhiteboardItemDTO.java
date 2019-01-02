package com.ochavoya.whiteboard.dto;

import com.ochavoya.whiteboard.entities.WhiteboardItemEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

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
    private String token;

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

    public String getToken() {
        return token;
    }
}
