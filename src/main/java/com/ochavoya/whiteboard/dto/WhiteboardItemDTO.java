package com.ochavoya.whiteboard.dto;

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
    private String details;
    private String token;
}
