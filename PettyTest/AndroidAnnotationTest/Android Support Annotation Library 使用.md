# Android Support Annotation Library 使用 #
该Library包含一系列有用的元注解，帮助开发者在编译期间发现可能存在的Bug，可提高代码质量。  

## 是否为空 ##

### @NonNull  不能为空  
### @Nullable 可以为空  

### 示例 ###

	@NonNull
    public final String getString() {
		//
    }

	public void setItemId(@NonNull String itemId) {
		//
    }

## 资源标记 ##

### @AnyRes  任何资源Id   

以及每种资源所对应的特有的标记

	@AnimatorRes, @AnimRes, @ArrayRes, @AttrRes, @BoolRes, @ColorRes, @DimenRes, @DrawableRes, @FractionRes, @IdRes,@IntegerRes, @InterpolatorRes, @LayoutRes, @MenuRes, @PluralsRes, @RawRes, @StringRes, @StyleableRes, @StyleRes, @XmlRes.

## 值标记 ##
### @ColorInt  颜色值int变量  
### @FlotRange和@IntRange 表示变量的取值范围  

	public void setAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        findViewById(R.id.tvInfo).setAlpha(alpha);
    }

## Proguard ##
###@keep
防止混淆  
使用该注解，在代码编写过程中对不需要混淆的类或者方法直接进行标记即可  

## 权限 ##
### RequiresPermission ###
声明 调用相关函数需要相关权限的时候，可使用该注解  

	//函数调用需要一个函数
    @RequiresPermission(Manifest.permission.SET_WALLPAPER)
    public void method1() {

    }

    //函数调用需要声明集合中最少一个权限
    @RequiresPermission(anyOf = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION})
    public void method2() {
    }

    //函数调用需要声明集合中最少一个权限
    @RequiresPermission(anyOf = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION})
    public void method3() {
        
    } 

## 单元测试 ##
### @VisibleForTesting ###
单元测试中访问一些不可见的类、函数或者变量，这时可以使用该注解来其对测试可见。

## 其他 ##
### 方法重写: @CallSuper ###

如果你的API允许使用者重写你的方法，但是呢，你又需要你自己的方法(父方法)在重写的时候也被调用，这时候你可以使用@CallSuper标注：

@CallSuper
protected void onCreate(@Nullable Bundle savedInstanceState) {
用了这个后，当重写的方法没有调用父方法时，工具就会给予标记提示：

![](http://www.linuxidc.com/upload/2015_08/150820193163764.png)  

### 返回值: @CheckResult ###
如果你的方法返回一个值，你期望调用者用这个值做些事情，那么你可以使用@CheckResult注解标注这个方法。

你并不需要微每个非空方法都进行标注。它主要的目的是帮助哪些容易被混淆，难以被理解的API的使用者。

比如，可能很多开发者都对String.trim()一知半解，认为调用了这个方法，就可以让字符串改变以去掉空白字符。如果这个方法被@CheckResult标注，工具就会对那些没有使用trim()返回结果的调用者发出警告。

Android中，Context#checkPermission这个方法已经被@CheckResult标注了：

@(suggest="#enforcePermission(String,int,int,String)")  
public abstract int checkPermission(@NonNull String permission, int pid, int uid);
这是非常重要的，因为有些使用context.checkPermission的开发者认为他们已经执行了一个权限 —-但其实这个方法仅仅只做了检查并且反馈一个是否成功的值而已。如果开发者使用了这个方法，但是又不用其返回值，那么这个开发者真正想调用的可能是这个Context#enforcePermission方法，而不是checkPermission。  

![](http://www.linuxidc.com/upload/2015_08/150820193163769.png)  

### 线程注解 ###

(Support library 22.2及其之后版本支持)  

如果你的方法只能在指定的线程类型中被调用，那么你就可以使用以下4个注解来标注它：

@UiThread
@MainThread
@WorkerThread
@BinderThread
如果一个类中的所有方法都有相同的线程需求，那么你可以注解类本身。比如android.view.View，就被用@UiThread标注。

关于线程注解使用的一个很好的例子就是AsyncTask：

@WorkerThread
protected abstract Result doInBackground(Params... params);

@MainThread
protected void onProgressUpdate(Progress... values) {
}
如果你在重写的doInBackground方法里尝试调用onProgressUpdate方法或者View的任何方法，IDE工具就会马上把它标记为一个错误：  

![](http://www.linuxidc.com/upload/2015_08/150820193163766.png)  

#### @UiThread还是@MainThread? ####

> 在进程里只有一个主线程。这个就是@MainThread。同时这个线程也是一个@UiThread。比如activity的主要窗口就运行在这个线程上。然而它也有能力为应用创建其他线程。这很少见，一般具备这样功能的都是系统进程。通常是把和生命周期有关的用@MainThread标注，和View层级结构相关的用@UiThread标注。但是由于@MainThread本质上是一个@UiThread，而大部分情况下@UiThread又是一个@MainThread，所以工具(lint ,Android Studio,等等)可以把他们互换，所以你能在一个可以调用@MainThread方法的地方也能调用@UiThread方法，反之亦然。

## 参考 ##
[Android注解支持(Support Annotations)](http://www.linuxidc.com/Linux/2015-08/121993.htm)  
[利用 Android Annotations 来玩玩契约编程](http://blog.csdn.net/feelang/article/details/49000203#rd)