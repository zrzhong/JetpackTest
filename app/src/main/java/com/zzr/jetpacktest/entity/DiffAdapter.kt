package com.zzr.jetpacktest.entity

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zzr.jetpacktest.R

/**
 * @Author zzr
 * @Desc
 * @Date 2020/10/10
 */
class DiffAdapter : BaseQuickAdapter<DiffBean, BaseViewHolder>(R.layout.item_test01) {
    override fun convert(holder: BaseViewHolder, item: DiffBean) {
        holder.setText(R.id.tvContent, item.desc)
    }
}