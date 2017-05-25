package com.lib.frame.view.changer;

import android.os.Bundle;

/**
 * Fragment状态保存
 *
 * @author EthanCo
 * @since 2016/11/16
 */

public interface IFragmentSave  {
    void restoreInstanceState(Bundle savedInstanceState);

    void saveInstanceState(Bundle outState);
}
