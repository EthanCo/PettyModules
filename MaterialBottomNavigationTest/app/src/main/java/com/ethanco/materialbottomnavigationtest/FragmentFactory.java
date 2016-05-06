package com.ethanco.materialbottomnavigationtest;

import android.app.Fragment;

/**
 * Created by EthanCo on 2016/5/7.
 */
public class FragmentFactory {
    public static Fragment createFactory(int type){
        switch (type) {
            case 1:
                return Fragment1.newInstance("Item"+type,"");
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
        return Fragment1.newInstance("Item"+type,"");
    }
}
