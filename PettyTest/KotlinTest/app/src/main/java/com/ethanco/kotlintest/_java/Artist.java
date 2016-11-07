package com.ethanco.kotlintest._java;

/**
 * Created by Zhk on 2016/11/7.
 */

public class Artist {
    private long id;
    private String name;
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
