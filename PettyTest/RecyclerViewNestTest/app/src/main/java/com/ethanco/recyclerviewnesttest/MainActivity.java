package com.ethanco.recyclerviewnesttest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.ethanco.recyclerviewnesttest.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Info> list;
    private List<String> imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(new MyAdapter(list, this));
    }

    private void initData() {
        imgList = new ArrayList<String>();
        imgList.add("http://img5.imgtn.bdimg.com/it/u=1660159292,2989295347&fm=11&gp=0.jpg");
        imgList.add("http://img1.2345.com/duoteimg/qqTxImg/2013/12/ns/18-024824_754.jpg");
        imgList.add("http://v1.qzone.cc/avatar/201408/17/14/22/53f04a277d3dd110.jpg%21200x200.jpg");
        imgList.add("http://image.tianjimedia.com/uploadImages/upload/20150312/0esyvzvilnejpg.jpg");
        imgList.add("http://imgsrc.baidu.com/forum/w%3D580/sign=3cc7d5f59c16fdfad86cc6e6848e8cea/5387e511728b4710223ad312c2cec3fdfd0323f9.jpg");
        imgList.add("http://tx.haiqq.com/uploads/allimg/150319/16131R155-5.jpg");

        list = new ArrayList<Info>();
        for (int i = 0; i < 20; i++) {
            list.add(new Info("Name" + i, imgList));
        }
    }
}
