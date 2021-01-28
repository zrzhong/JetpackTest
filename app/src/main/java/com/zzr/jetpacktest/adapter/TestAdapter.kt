package com.zzr.jetpacktest.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zzr.jetpacktest.R

/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/17
 */
class TestAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_test01) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvContent, item)
    }
}