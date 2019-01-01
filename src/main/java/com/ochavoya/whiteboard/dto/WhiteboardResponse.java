package com.ochavoya.whiteboard.dto;

public class WhiteboardResponse {
    private Boolean success;
    private String message;

    public WhiteboardResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
