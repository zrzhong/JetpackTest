package com.zzr.jetpacktest.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.OverScroller
import androidx.core.view.NestedScrollingParent
import androidx.core.view.NestedScrollingParentHelper

/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/17
 */
class MyNestedScrollParent : LinearLayout, NestedScrollingParent {
    private lateinit var img: View
    private var myNestedScrollChild: MyNestedScrollChild? = null
    private var mNestedScrollingParentHelper: NestedScrollingParentHelper? = null
    private var topHeight = 0

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        mNestedScrollingParentHelper = NestedScrollingParentHelper(this)
    }

    private val mScroller: OverScroller by lazy {
        OverScroller(context)
    }

    //当view加载完成时获取子view
    override fun onFinishInflate() {
        super.onFinishInflate()

        //获取第一个子view，ImageView
        img = getChildAt(0)

        //获取第三个子view，MyCustomNestedScrollingChild
        myNestedScrollChild = getChildAt(2) as MyNestedScrollChild
        img.viewTreeObserver.addOnGlobalLayoutListener {
            //当布局变化时，获取图片布局的高度
            if (topHeight <= 0) {
                topHeight = img.measuredHeight
            }
            //                img.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    //在此可以判断参数target是哪一个子view以及滚动的方向，然后决定是否要配合其进行嵌套滚动
    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        return target is MyNestedScrollChild
    }

    override fun onNestedScrollAccepted(child: View, target: View, nestedScrollAxes: Int) {
        mNestedScrollingParentHelper!!.onNestedScrollAccepted(child, target, nestedScrollAxes)
    }

    override fun onStopNestedScroll(target: View) {
        mNestedScrollingParentHelper!!.onStopNestedScroll(target)
    }

    //先于child滚动
    //前3个为输入参数，最后一个是输出参数
    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {

        //在子view进行滚动之后调用此方法，询问父view是否还要进行余下(unconsumed)的滚动。
        //前四个参数为输入参数，用于告诉父view已经消费和尚未消费的距离，最后一个参数为输出参数，用于子view获取父view位置的偏移量。
        //如果父view接收了它的滚动参数，进行了部分消费，则这个函数返回true，否则为false
        Log.i("NestedScrolling", "onNestedPreScroll: ")
        if (showImg(dy) || hideImg(dy)) { //根据图片的高度判断上拉和下拉的处理
            scrollBy(0, -dy)
            consumed[1] = dy //告诉child消费了多少距离
        }
    }

    //后于child滚动
    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        Log.i("NestedScrolling", "onNestedPreScroll: ")
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        if (scrollY >= topHeight) return false
        fling(velocityY.toInt())
        return true
//        //是否消费了手指滑动事件
//        return false
    }

    private fun fling(velocityY: Int) {
        mScroller.fling(0, scrollY, 0, velocityY, 0, 0, 0, topHeight)
        invalidate()
    }

    override fun onNestedFling(
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        //是否消费了手指滑动事件
        return false
    }

    override fun getNestedScrollAxes(): Int {
        return mNestedScrollingParentHelper!!.nestedScrollAxes
    }

    //下拉的时候是否要向下滚动以显示图片
    private fun showImg(dy: Int): Boolean {
        return if (dy > 0) {
            Log.i("onTouchEvent", "scrollY下拉: $scrollY")
            scrollY > 0 && myNestedScrollChild!!.scrollY == 0
        } else false
    }

    //上拉的时候，是否要向上滚动，隐藏图片
    private fun hideImg(dy: Int): Boolean {
        return if (dy < 0) {
            Log.i("onTouchEvent", "scrollY上拉: $scrollY")
            scrollY < topHeight
        } else false
    }

    //限制滚动范围，防止出现偏差
    override fun scrollTo(x: Int, y: Int) {
        val temp = y.coerceAtLeast(0)
        val temp2 = temp.coerceAtMost(topHeight)

//        if (newY < 0) {
//            newY = 0
//        }
//        if (newY > topHeight) {
//            newY = topHeight
//        }
        super.scrollTo(x, temp2)
    }
}