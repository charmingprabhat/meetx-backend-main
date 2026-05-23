package com.videomeeting.backend.signaling;

public class SignalMessage {

    private String sender;

    private String roomId;

    private String type;

    private Object data;

    private String message;

    private Boolean muted;

    private Boolean videoOff;

    public SignalMessage() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Boolean getMuted() {
        return muted;
    }

    public void setMuted(Boolean muted) {
        this.muted = muted;
    }

    public Boolean getVideoOff() {
        return videoOff;
    }

    public void setVideoOff(Boolean videoOff) {
        this.videoOff = videoOff;
    }
}