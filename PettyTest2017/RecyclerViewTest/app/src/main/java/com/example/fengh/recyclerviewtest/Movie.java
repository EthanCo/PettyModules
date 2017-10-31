package com.example.fengh.recyclerviewtest;

import android.databinding.ObservableField;

/**
 * Created by luoxiongwen on 16/10/24.
 */

public class Movie {

    public ObservableField<String> name = new ObservableField();
    //public String name;
    public int length;
    public int price;
    public String content;

    public Movie(String name, int length, int price, String content) {
        this.length = length;
        this.name.set(name);
        this.price = price;
        this.content = content;
    }

    public void setName(String name){
        this.name.set(name);
    }
}
