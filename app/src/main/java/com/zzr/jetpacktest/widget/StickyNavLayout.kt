package com.zzr.jetpacktest.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.NestedScrollingParent2
import androidx.core.view.NestedScrollingParentHelper
import androidx.core.view.ViewCompat
import com.zzr.jetpacktest.R


/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/20
 */
class StickyNavLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), NestedScrollingParent2 {
    init {
        orientation = VERTICAL
    }

    //与展示图片高度相同的透明View
    private lateinit var mTopView: View

    //tabLayout
    private lateinit var mNavView: View

    //viewPager
    private lateinit var mViewPager: View

    //父控件可以滑动的距离
    private var mCanScrollDistance: Float = 0f

    private var mScrollChangeListener: ScrollChangeListener? = null


    private val mNestedScrollingParentHelper: NestedScrollingParentHelper by lazy {
        NestedScrollingParentHelper(this)
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
//        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
        return (axes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type)
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        mNestedScrollingParentHelper.onStopNestedScroll(target, type)
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    override fun onNestedFling(
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return false
    }

    override fun getNestedScrollAxes(): Int {
        return mNestedScrollingParentHelper.nestedScrollAxes
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        //表示已经向下滑动到头，且为fling
        if (dyUnconsumed < 0 && type == ViewCompat.TYPE_NON_TOUCH) {
            scrollBy(0, dyUnconsumed)
        }
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        //向上 父view拦截事件的条件
        val hideTop = dy > 0 && scrollY < mCanScrollDistance
        //向下
        val showTop = dy < 0 && scrollY > 0 && !target.canScrollVertically(-1)
        if (hideTop || showTop) {
            scrollBy(0, dy)
            consumed[1] = dy
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mTopView = findViewById(R.id.sl_top_view)
        mNavView = findViewById(R.id.tabLayout)
        mViewPager = findViewById(R.id.vpDemo)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mCanScrollDistance =
            mTopView.measuredHeight - resources.getDimension(R.dimen.normal_title_height)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //先测量一次
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //ViewPager修改后的高度= 总高度-TabLayout的高度
        val lp: ViewGroup.LayoutParams = mViewPager.layoutParams
        lp.height = measuredHeight - mNavView.measuredHeight
        mViewPager.layoutParams = lp
        //因为ViewPager修改了高度，所以需要重新测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    //对父控件的滚动范围进行校验 滚动范围为0-mCanScrollDistance
    override fun scrollTo(x: Int, y: Int) {
        var newY = y
        newY = newY.coerceAtLeast(0)
        newY = newY.coerceAtMost(mCanScrollDistance.toInt())

        //父view用scrollBy滚动，内部调用的是scrollTo
        //计算比例
        Log.i("scrollTo", "y: $newY ,scrollY: $scrollY")
        mScrollChangeListener?.onScroll(scrollY / mCanScrollDistance)

        if (scrollY != newY) super.scrollTo(x, newY)

    }

    interface ScrollChangeListener {
        /**
         * 移动监听
         *
         * @param moveRatio 移动比例
         */
        fun onScroll(moveRatio: Float)
    }

    fun setScrollChangeListener(scrollChangeListener: ScrollChangeListener) {
        mScrollChangeListener = scrollChangeListener
    }
}