package com.ethanco.compositepattern.file_system;

import java.util.List;

/**
 * Created by EthanCo on 2016/7/17.
 */
public class File extends Dir {

    public File(String name) {
        super(name);
    }

    @Override
    public void addDir(Dir dir) {
        throw new UnsupportedOperationException("文件不支持该操作");
    }

    @Override
    public void rmDir(Dir dir) {
        throw new UnsupportedOperationException("文件不支持该操作");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("文件不支持该操作");
    }

    @Override
    public void print() {
        System.out.print(getName());
    }

    @Override
    public List<Dir> getFiles() {
        throw new UnsupportedOperationException("文件不支持该操作");
    }
}
