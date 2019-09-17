package com.ethanco.bottomsheettest;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GravityCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import java.util.List;

public class BottomBehavior extends CoordinatorLayout.Behavior<View> {
    public BottomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    // =============== 根本：添加依赖 =============================
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.scroll;
    }

    // ================ 第一部分：定位 ========================
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        // getDependencies  获取child 依赖的view
        List<View> dependencies = parent.getDependencies(child);
//        parent.getDependents(child)  // 获取依赖child 的控件
        View header = findFirstDependency(dependencies);
        if (header != null) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            Rect available = new Rect();
            available.set(parent.getPaddingLeft() + lp.leftMargin,
                    header.getBottom() + lp.topMargin,
                    parent.getWidth() - parent.getPaddingRight() - lp.rightMargin,
                    parent.getHeight() - parent.getPaddingBottom() - lp.bottomMargin);
            final Rect out = new Rect();
            GravityCompat.apply(resolveGravity(getFinalGravity(lp.gravity)), child.getMeasuredWidth(),
                    child.getMeasuredHeight(), available, out, layoutDirection);
            child.layout(out.left, out.top, out.right, out.bottom);
        } else {
            super.onLayoutChild(parent, child, layoutDirection);
        }
        return true;
    }

    public int getFinalGravity(int gravity) {
        // 获取当前控件的`layout_gravity`属性
        if ((gravity & Gravity.VERTICAL_GRAVITY_MASK) == 0) {
            gravity = gravity | Gravity.TOP;
        }
        if ((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == 0) {
            gravity = gravity | Gravity.LEFT;
        }
        return gravity;
    }

    private static int resolveGravity(int gravity) {
        return gravity == Gravity.NO_GRAVITY ? GravityCompat.START | Gravity.TOP : gravity;
    }

    private View findFirstDependency(List<View> views) {
        // 查找最根本的依赖
        for (View view : views) {
            if (view.getId() == R.id.scroll) {
                return view;
            }
        }
        return null;
    }
    // ================ 第二部分：监听移动 ========================
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY(dependency.getTranslationY());
        return true;
    }
}

