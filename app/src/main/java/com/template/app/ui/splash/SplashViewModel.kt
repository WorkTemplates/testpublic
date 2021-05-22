package com.template.app.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.template.app.lifecycle.LiveEvent
import com.template.app.navigation.NavCmd
import com.template.app.navigation.SplashToMainCmd
import com.template.app.navigation.SplashToNoAccess
import com.template.app.navigation.SplashToWebView
import com.template.app.repository.AccessRepository

class SplashViewModel(val repo: AccessRepository): ViewModel() {

    private val _navigation =  LiveEvent<NavCmd>()
    val navigation: LiveData<NavCmd> = _navigation

    fun update(){
        _navigation.addSource(repo.getAccess()) {
            if (it.isValid == true) {
                if (it.url != null) {
                    _navigation.postValue(SplashToWebView(it.url))
                } else {
                    _navigation.postValue(SplashToMainCmd())
                }
            } else {
                _navigation.postValue(SplashToNoAccess())
            }
        }
    }

}
