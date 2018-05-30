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

> 该项目后来单独列为一个Github 项目，[在此处](https://github.com/EthanCo/NovaRecyclerView)  

## LocateCityAndWeather ##

1. 通过外网IP定位城市
2. 通过城市获取天气  

**需注册账号后填写相关的key**  

可以看我写的博文 [Android 通过外网IP定位城市](http://blog.csdn.net/ethanco/article/details/52777032)  

## AndroidAnnotationLibrary ##
该Library包含一系列有用的元注解，帮助开发者在编译期间发现可能存在的Bug，可提高代码质量。  
具体看[AndroidAnnotationLibrary](http://blog.csdn.net/EthanCo/article/details/52931166)

## BuglyAppUpdate ##
对腾讯Bugly封装的Lib，支持Crash上报、应用升级。  

### 使用 ###
引用lib.bugly  

修改lib.bugly.AndroidManifest的TODO部分  

然后  

在Application#onCreate()中初始化，这会初始化Bulgy并自动进行应用更新的检查  

	//使用自定义对话框	
	BuglyWrap.initAndCustomDialog(getApplicationContext(), "7f525d2734", false, R.mipmap.ic_launcher);  

如果不使用自定义的对话框，则可使用BuglyWrap.init();  

**设置渠道号**  

可根据渠道号进行应用升级的推送  

	CrashReport.setAppChannel(this, "Test");   

**手动检查**

	Beta.checkUpgrade();

## LayoutManagerGroup
![LayoutManagerGroup](https://github.com/DingMouRen/LayoutManagerGroup/raw/master/picture/img1.gif)  

使用Recyclerview实现炫酷滑动列表，详见[LayoutManagerGroup](https://github.com/DingMouRen/LayoutManagerGroup)



