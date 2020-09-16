package com.urms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JsonObject<T> implements Serializable {
    private String message;
    private Integer state;
    private Date currentTime;
    private Map<String,Object> box;
    private List<Object> data;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }


    public Map<String, Object> getBox() {
        return box;
    }

    public void setBox(Map<String, Object> box) {
        this.box = box;
    }


    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
