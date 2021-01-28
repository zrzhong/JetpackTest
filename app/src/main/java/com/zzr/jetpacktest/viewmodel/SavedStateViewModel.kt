package com.zzr.jetpacktest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * @Author zzr
 * @Desc activity销毁重建时恢复数据
 * @Date 2020/10/14
 */
class SavedStateViewModel(private val state: SavedStateHandle) : ViewModel() {

    companion object {
        const val SAVE_STATE_KEY_STRING = "saved_state_handle_user_name"
        const val SAVE_STATE_KEY_LIVEDATE = "saved_state_handle_user_name_livedata"
    }

    var userName: String?
        get() = state.get(SAVE_STATE_KEY_STRING)
        set(value) = state.set(SAVE_STATE_KEY_STRING, value)

    fun getUserNameLiveData(): LiveData<String> = state.getLiveData(SAVE_STATE_KEY_LIVEDATE)

    fun saveUserNameLiveData(newName: String) = state.set(SAVE_STATE_KEY_LIVEDATE, newName)


}