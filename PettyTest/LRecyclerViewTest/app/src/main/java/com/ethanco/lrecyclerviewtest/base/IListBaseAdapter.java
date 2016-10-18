package com.ethanco.lrecyclerviewtest.base;


import com.ethanco.lrecyclerviewtest.bean.Entity;

import java.util.Collection;
import java.util.List;

public interface IListBaseAdapter<T extends Entity> {

    List<T> getDataList();

    void setDataList(Collection<T> list);

    void addAll(Collection<T> list);

    void remove(int position);

    void clear();
}
