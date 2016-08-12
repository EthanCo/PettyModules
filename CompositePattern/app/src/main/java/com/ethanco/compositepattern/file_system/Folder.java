package com.ethanco.compositepattern.file_system;

import java.util.Iterator;
import java.util.List;

/**
 * Created by EthanCo on 2016/7/17.
 */
public class Folder extends Dir {
    public Folder(String name) {
        super(name);
    }

    @Override
    public void addDir(Dir dir) {
        dirs.add(dir);
    }

    @Override
    public void rmDir(Dir dir) {
        dirs.remove(dir);
    }

    @Override
    public void clear() {
        dirs.clear();
    }

    @Override
    public void print() {
        System.out.print(getName() + "(");
        Iterator<Dir> iter = dirs.iterator();
        while (iter.hasNext()) {
            Dir dir = iter.next();
            dir.print();
            if (iter.hasNext()) {
                System.out.print(", ");
            }
        }
        System.out.println(")");
    }

    @Override
    public List<Dir> getFiles() {
        return dirs;
    }
}
