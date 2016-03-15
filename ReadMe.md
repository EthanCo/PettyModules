# PettyModules #
## PointRecyclerView ##
具有不同的圆点的Recycleview  

![PointRecyclerView](/PointRecyclerView/PointRecyclerView.gif)

## MaterialdesignTab ##
基于Material Disign TabLayout 封装的一个 Module

![](/MaterialdesignTab/MaterialdesignTab.gif)

###使用

在xml中声明  

	<com.ethanco.mylib.SimpleTabLayout
        android:id="@+id/simpleTabLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"></com.ethanco.mylib.SimpleTabLayout>

在代码中设置  

	simpleTabLayout =(SimpleTabLayout) findViewById(R.id.simpleTabLayout);
    simpleTabLayout.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),this));

其中MyFragmenPagerAdapter是继承自BasePagerAdapter