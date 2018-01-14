package com.example.savingdatausingsqlite.bean;

/**
 * Created by aruna on 1/14/18.
 */

public class ReadData {
    private String id;
    private String title;
    private String subTitle;

    public ReadData(String id, String title, String subTitle) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }
}
