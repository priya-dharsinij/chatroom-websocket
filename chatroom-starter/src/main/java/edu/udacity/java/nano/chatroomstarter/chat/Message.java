package edu.udacity.java.nano.chatroomstarter.chat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * WebSocket message model
 */
public class Message {

    @JSONField
    private String username;
    @JSONField
    private String content;
    @JSONField
    private String type;
    @JSONField
    private String onlineCount;

    public Message() {
    }

    public Message(String username, String content, String type, String onlineCount) {
        this.username = username;
        this.content = content;
        this.type = type;
        this.onlineCount = onlineCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(String onlineCount) {
        this.onlineCount = onlineCount;
    }
}
