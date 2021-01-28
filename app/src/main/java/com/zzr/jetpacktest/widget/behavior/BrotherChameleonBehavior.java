package com.zzr.jetpacktest.widget.behavior;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.zzr.jetpacktest.widget.DependedView;

/**
 * @Author zzr
 * @Desc 变色
 * @Date 2020/11/27
 */
public class BrotherChameleonBehavior extends CoordinatorLayout.Behavior<View> {
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public BrotherChameleonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof DependedView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        int color = (int) argbEvaluator.evaluate(dependency.getY() / parent.getHeight(), Color.WHITE, Color.BLACK);
        child.setBackgroundColor(color);
        //跟随dependency的位置大小变化才返回true
        return false;
    }
}
