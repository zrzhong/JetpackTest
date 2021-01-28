package com.zzr.jetpacktest.entity

import androidx.recyclerview.widget.DiffUtil

/**
 * @Author zzr
 * @Desc
 * @Date 2020/10/10
 */
class DiffCallBack(val oldData: MutableList<DiffBean>, val newData: MutableList<DiffBean>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].name == newData[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldData[oldItemPosition].desc != newData[newItemPosition].desc) {
            return false
        }
        if (oldData[oldItemPosition].name != newData[newItemPosition].name) {
            return false
        }
        return true
    }

}