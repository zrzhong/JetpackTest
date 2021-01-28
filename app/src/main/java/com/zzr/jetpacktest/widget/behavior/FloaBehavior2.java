package com.zzr.jetpacktest.widget.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/27
 */
public class FloaBehavior2 extends CoordinatorLayout.Behavior<View> {
    public FloaBehavior2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        //根据dependency top值的变化改变 child 的 translationY
        float translationY = Math.abs(dependency.getTop());
        child.setScaleX(translationY/dependency.getHeight());
        child.setScaleY(translationY/dependency.getHeight());
        Log.i("FloaBehavior", "top: "+dependency.getTop());
        return true;
    }
}
