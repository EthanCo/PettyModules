package com.example.fengh.recyclerviewtest;

import java.util.List;


interface RequestCallBack {
    void success(List<Status> data);

    void fail(Exception e);
}

