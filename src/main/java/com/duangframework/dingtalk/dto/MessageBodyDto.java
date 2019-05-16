package com.duangframework.dingtalk.dto;

/**
 *
 */
public class MessageBodyDto implements java.io.Serializable {

    public enum Type {
        TEXT,IMAGE,FILE,LINK,MARKDOWN,OA,ACTION_CARD
    }

    private Type type;
    private String content;

    public MessageBodyDto() {
    }

    public MessageBodyDto(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
