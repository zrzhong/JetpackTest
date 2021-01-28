package com.zzr.jetpacktest.widget

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zzr.jetpacktest.entity.CityBean

/**
 * @Author zzr
 * @Desc
 * @Date 2020/10/9
 */
class MyItemDecoration(val mData: MutableList<CityBean>) : RecyclerView.ItemDecoration() {
    private val mHeight = 80
    private val paint = Paint()
    private val mRect = Rect()
    private val COLOR_TITLE_BG: Int = android.graphics.Color.parseColor("#FFDFDFDF")
    private val COLOR_TITLE_FONT: Int = android.graphics.Color.parseColor("#FF000000")


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
        if (position > -1) {
            if (position == 0) {
                outRect.set(0, mHeight, 0, 0)
            } else {
                //tag不为空且跟前一个不一样，需要title
                if (mData[position].tag != mData[position - 1].tag) {
                    outRect.set(0, mHeight, 0, 0)
                }
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft.toFloat()
        val right = (parent.right - parent.paddingRight).toFloat()
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val position = params.viewLayoutPosition
            if (position > -1) {
                if (position == 0) {
                    drawTextArea(c, left, child, params, right, position)
                } else {
                    //tag不为空且跟前一个不一样，需要title
                    if (mData[position].tag != mData[position - 1].tag) {
                        drawTextArea(c, left, child, params, right, position)
                    }
                }
            }
        }
    }

    private fun drawTextArea(
        c: Canvas,
        left: Float,
        child: View,
        params: RecyclerView.LayoutParams,
        right: Float,
        position: Int
    ) {
        paint.color = COLOR_TITLE_BG
        c.drawRect(
            left,
            (child.top - params.topMargin - mHeight).toFloat(),
            right,
            (child.top - params.topMargin).toFloat(),
            paint
        );
        paint.color = COLOR_TITLE_FONT;
        paint.getTextBounds(mData[position].tag, 0, mData[position].tag.length, mRect);
        c.drawText(
            mData[position].tag,
            child.paddingLeft.toFloat(),
            (child.top - params.topMargin - (mHeight / 2 - mRect.height() / 2)).toFloat(),
            paint
        );
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val position = (parent.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        val tag = mData[position].tag
        val child = parent.findViewHolderForLayoutPosition(position)!!.itemView

        paint.color = COLOR_TITLE_BG
        c.drawRect(
            parent.paddingLeft.toFloat(),
            parent.paddingTop.toFloat(),
            (parent.right - parent.paddingRight).toFloat(),
            (parent.paddingTop
                    + mHeight).toFloat(),
            paint
        )
        paint.color = COLOR_TITLE_FONT
        paint.textSize = 40f
        paint.getTextBounds(tag, 0, tag.length, mRect)
        c.drawText(
            tag,
            child.paddingLeft.toFloat(),
            (parent.paddingTop + mHeight - (mHeight / 2 - mRect.height() / 2)).toFloat(),
            paint
        )
    }
}