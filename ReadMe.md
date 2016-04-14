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

> 关于Material Design Tab 的使用 : [传送门](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html)

## SlideUnlock ##
一个竖直方向的滑动解锁，采用Scroller来实现滑动

## Dagger2Sample-coffee ##
Dagger2经典的coffee示例

## MyMaterialDialogTest ##
基于[material-dialogs](https://github.com/afollestad/material-dialogs#input-dialogs)的一个示例项目  
material dialogs 是 material 风格的dialog

## MRecyclerViewSample ##
封装了下拉自动加载的RecyclerView

## SlideAdSample ##
图片轮播(需在Application中进行Fresco的初始化)  
![SlideAdSample](/SlideAdSample/SlideAdSample.gif)