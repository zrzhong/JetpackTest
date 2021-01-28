package com.zzr.jetpacktest.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel : ViewModel() {
    private val _selectedData = MutableLiveData<String>()

    val selectedData: LiveData<String>
        get() = _selectedData

    private var count = 0

    fun setNewValue() {
        _selectedData.value = "New Value-${count++}"
    }
}