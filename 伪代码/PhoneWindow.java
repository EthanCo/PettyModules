package com.android.internal.policy.impl;
//Window的实现类
public class PhoneWindow extends Window implements MenuBuilder.Callback {
	
    //window的顶级视图
    private DecorView mDecor;

	//mContentRoot:是DecorView的唯一子视图,内部包含mContentParent
    private ViewGroup mContentRoot;
	
	//mContentRoot的其中一个视图，即setContentView时的视图，id为com.android.internal.R.id.content
	//NOTE:id也有可能不是com.android.internal.R.id.content，可能是包含了com.android.internal.R.id.content的父View的ID
	//比如而5.0上在一些情况下会为R.id.decor_content_parent，其内部才是R.id.content
	//详细看Android5.0  .\frameworks\base\core\res\res\layout\screen_toolbar.xml
    private ViewGroup mContentParent;

    @Override
    public void setContentView(int layoutResID) {
        //是否是第一次调用setContentView方法， 如果是第一次调用，则mDecor和mContentParent对象都为空  
		if (mContentParent == null) {  //首先判断mContentParent是否为null，是则调用installDecor()
			installDecor();  
		} else {  //否则移除其内部所有的子Views
			mContentParent.removeAllViews();  
		}

        if (hasFeature(FEATURE_CONTENT_TRANSITIONS)) {
            final Scene newScene = Scene.getSceneForLayout(mContentParent, layoutResID,
                    getContext());
            transitionTo(newScene);
        } else {
			//inflate layoutResID
            mLayoutInflater.inflate(layoutResID, mContentParent);
        }
        final Callback cb = getCallback();
        if (cb != null && !isDestroyed()) {
            cb.onContentChanged();
        }
    }

    @Override
    public void setContentView(View view) {
        setContentView(view, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        if (mContentParent == null) {
            installDecor();
        }
        if (hasFeature(FEATURE_CONTENT_TRANSITIONS)) {
            // TODO Augment the scenes/transitions API to support this.
            Log.v(TAG, "addContentView does not support content transitions");
        }
        mContentParent.addView(view, params);
        final Callback cb = getCallback();
        if (cb != null && !isDestroyed()) {
            cb.onContentChanged();
        }
    }
	
	//[window]创建一个decorView
	private void installDecor() {
		if (mDecor == null) {
			mDecor = generateDecor(); //z-如果没有DecorView，那么就创建它
			....
		}
		if (mContentParent == null) {
			//[window] 这一步也是很重要的.
			mContentParent = generateLayout(mDecor); //mContentParent是setContentVIew的关键
			.....
		}
	}
	
	protected ViewGroup generateLayout(DecorView decor) {
    // Apply data from current theme.
    .......
    //[window] 根据不同的style生成不同的decorview啊
    View in = mLayoutInflater.inflate(layoutResource, null);
    // 加入到decor中,所以应该是其第一个child
    decor.addView(in, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
    mContentRoot = (ViewGroup) in; //给DecorView的第一个child是mContentView
    // 这是获得所谓的content 
    ViewGroup contentParent = (ViewGroup)findViewById(ID_ANDROID_CONTENT);
    }
    .....
    return contentParent;
}
	
	//当Activity触发点击事件时调用 Activity#dispatchTouchEvent -> window.superDispatchTouchEvent
	@Override
    public boolean superDispatchTouchEvent(MotionEvent event) {
        return mDecor.superDispatchTouchEvent(event);
    }
	
	//DecorView 所有窗口的底层容器 (即setContentView所设置的View的父容器)
	private final class DecorView extends FrameLayout implements RootViewSurfaceTaker {
        public boolean superDispatchTouchEvent(MotionEvent event) {
			//开始DecorView的事件分发
            return super.dispatchTouchEvent(event);
        }
    }
}
