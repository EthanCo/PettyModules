package com.ethanco.compositepattern.file_system;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EthanCo on 2016/7/17.
 */
public abstract class Dir {
    /**
     * 声明一个List成员变量存储文件夹下的所有元素
     */
    protected List<Dir> dirs = new ArrayList<>();

    private String name; //当前文件或文件夹名

    public Dir(String name) {
        this.name = name;
    }

    /**
     * 添加一个文件或文件夹
     */
    public abstract void addDir(Dir dir);

    /**
     * 移除一个文件或文件夹
     */
    public abstract void rmDir(Dir dir);

    /**
     * 清除文件夹下所有的元素
     */
    public abstract void clear();

    /**
     * 输出文件夹目录结构
     */
    public abstract void print();

    /**
     * 获取文件夹下所有的文件或子文件夹
     */
    public abstract List<Dir> getFiles();

    /**
     * 获取文件或文件夹名
     *
     * @return 文件或文件夹名
     */
    public String getName() {
        return name;
    }
}
