package com.template.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.template.app.lifecycle.LiveEvent
import com.template.app.model.Data
import com.template.app.navigation.NavCmd
import com.template.app.navigation.WebViewCmd
import com.template.app.ui.main.data.ClickCallback

class MainViewModel : ViewModel(), ClickCallback {

    private val _navigation =  LiveEvent<NavCmd>()
    val navigation: LiveData<NavCmd> = _navigation


    override fun onClick(data: Data) {
        _navigation.postValue(WebViewCmd(data.url))
    }



}
