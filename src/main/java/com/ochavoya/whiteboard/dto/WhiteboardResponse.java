package com.ochavoya.whiteboard.dto;

public class WhiteboardResponse {
    private Boolean success;
    private Object data;

    public WhiteboardResponse(Boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }
}
