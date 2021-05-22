package com.template.app.repository

import androidx.lifecycle.LiveData
import com.template.app.model.Data

interface NetworkRepository {
    fun getData(): LiveData<List<Data>>
}
