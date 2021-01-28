package com.zzr.jetpacktest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.ViewCompat;

import com.zzr.jetpacktest.R;

/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/20
 */
public class NestedScrollingLayout extends FrameLayout implements NestedScrollingParent {

    private static final String TAG = "StickyNavLayout";

    /**
     * 是否是往下拉
     */
    private boolean mShowTop = false;
    /**
     * 是否是往上滑
     */
    private boolean mHideTop = false;

    /**
     * 外部View滑动的最大距离
     */
    private int mTopViewHeight;

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int nestedScrollAxes) {
    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i("NestedScrolling", "onNestedScroll: ");
    }

    /**
     * dx和dy参数表示滑动的横向和纵向距离，consumed参数表示消耗的横向和纵向距离，如纵向滚动，只需要消耗了dy/2，表示父View和内部View分别处理这次滚动距离的 1/2
     *
     * @param target   内部View
     * @param dx       滑动的横向距离
     * @param dy       滑动的纵向距离
     * @param consumed 外部View消耗的横向和纵向距离
     */
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        Log.i("NestedScrolling", "onNestedPreScroll: ");
//        Log.i(TAG, "dy: " + dy + "     scrollY: " + getScrollY());
        mShowTop = dy < 0 && Math.abs(getScrollY()) < mTopViewHeight && !target.canScrollVertically(-1);//往下拉
        if (mShowTop) {
            if (Math.abs(getScrollY() + dy) > mTopViewHeight) {//如果超过了指定位置
                dy = -(mTopViewHeight - Math.abs(getScrollY()));//滑动到指定位置
            }
        }
        mHideTop = dy > 0 && getScrollY() < 0;//往上滑
        if (mHideTop) {
            if (dy + getScrollY() > 0) {//如果超过了初始位置
                dy = -getScrollY();//滑动到初始位置
            }
        }
        if (mShowTop || mHideTop) {
            consumed[1] = dy;//消耗纵向距离
            scrollBy(0, dy);//外部View滚动
        }
    }


    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return getScrollY() != 0;
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return getScrollY() != 0;
    }

    @Override
    public int getNestedScrollAxes() {
        return ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    public NestedScrollingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollingLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopViewHeight = getResources().getDimensionPixelSize(R.dimen.dp_256);
    }
}
