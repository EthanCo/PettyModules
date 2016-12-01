package com.ethanco.recyclerviewnesttest;

import java.util.List;

/**
 * Created by EthanCo on 2016/12/1.
 */

public class Info {
    private String name;
    private List<String> imgList;

    public Info(String name, List<String> imgList) {
        this.name = name;
        this.imgList = imgList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
