package com.zzr.jetpacktest.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.entity.CityBean

/**
 * @Author zzr
 * @Desc
 * @Date 2020/10/9
 */
class MyAdapter : BaseQuickAdapter<CityBean, BaseViewHolder>(R.layout.item_test01) {
    override fun convert(holder: BaseViewHolder, item: CityBean) {
        holder.setText(R.id.tvContent, item.city)
    }
}