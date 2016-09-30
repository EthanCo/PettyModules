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

## GridContainerViewSample ##
格子容器View，为了解决RecyclerView嵌套冲突自己写的一个Grid列表

## MaterialBottomNavigationTest ##
Material disign 的底部导航栏

## ImageTest ##
对图片加载库的包装  

## ImageProxySample ##
对图片加载库的包装 V2 版本

## ExpandableListViewTest ##
可展开的列表Demo

## BottomSheetDemo ##
Bottom sheet 的一个示例，在support library23.2中，增加了Bottom sheet

![BottomSheetDemo](/BottomSheetDemo/BottomSheetDemo.gif)  

## NovaRecyclerview ##
对LRecyclerview第三方库的二次封装  

**[LRecyclerView](https://github.com/jdsjlzx/LRecyclerView)**
  
1. 下拉刷新、滑动到底部自动加载下页数据；  
1. 可以方便添加Header和Footer；  
1. 头部下拉样式可以自定义；  
1. 具备item点击和长按事件；  
1. 网络错误加载失败点击Footer重新请求数据；    
1. 可以动态为FooterView赋予不同状态（加载中、加载失败、滑到最底等）；    

**通过封装，使其代码更优雅，编写代码更小，接口更小，耦合更低，更适合于公司项目开发**