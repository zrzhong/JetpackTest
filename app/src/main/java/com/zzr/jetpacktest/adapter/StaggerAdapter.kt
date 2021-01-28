package com.zzr.jetpacktest.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zzr.jetpacktest.R

/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/10
 */
class StaggerAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_stagger) {
    private val array = intArrayOf(R.drawable.pic001, R.drawable.pic002, R.drawable.pic003)

    override fun convert(holder: BaseViewHolder, item: String) {
        val ivStaggered = holder.getView<ImageView>(R.id.ivStaggered)
        val params: ViewGroup.LayoutParams = ivStaggered.layoutParams
        val height = when (holder.adapterPosition % 5) {
            0 -> 500
            1 -> 750
            2 -> 880
            3 -> 360
            4 -> 660
            else -> 300
        }
        params.width = height
        ivStaggered.layoutParams = params

        ivStaggered.setImageResource(array[holder.adapterPosition % array.size])
    }
}