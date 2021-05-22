package com.template.app.repository

import androidx.lifecycle.LiveData

interface AccessRepository {
    fun getAccess(): LiveData<Access>
}

data class Access(val isValid: Boolean, val url: String? = null)
