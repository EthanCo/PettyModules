package com.ethanco.expandablelistviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this);
        listView.setAdapter(adapter);

        //设置 属性 GroupIndicator 去掉默认向下的箭头
        //listView.setGroupIndicator(null);

        //默认展开 第0组
        //listView.expandGroup(0);

        //默认展开所有组
        /*for (int i = 0; i < adapter.getGroupCount(); i++) {
            listView.expandGroup(i);
        }*/

        //关于ExpandableListView刷新的解决办法 http://blog.csdn.net/arylo/article/details/8711769
        /*if (listView.isGroupExpanded(1)) {
            listView.collapseGroup(1);
            listView.expandGroup(1);
        } else {
            listView.expandGroup(1);
            listView.collapseGroup(1);
        }*/
    }
}
