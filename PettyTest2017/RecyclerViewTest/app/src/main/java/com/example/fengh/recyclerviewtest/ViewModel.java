package com.example.fengh.recyclerviewtest;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/10/20
 */

public class ViewModel {
    public final ObservableList<String> items = new ObservableArrayList<>();
    public final ItemBinding<String> itemBinding = ItemBinding.of(com.example.fengh.recyclerviewtest.BR.item, R.layout.item);
    public final ItemBinding<String> itemBinding2 = ItemBinding.of(new OnItemBind<String>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, String item) {
           /* itemBinding.clearExtras()
                    .set(BR.vm, R.layout.alink_item_alink_music)
                    .bindExtra(BR.listener, musicListener);*/
        }
    });

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem() {
        items.remove(items.size() - 1);
    }
}
