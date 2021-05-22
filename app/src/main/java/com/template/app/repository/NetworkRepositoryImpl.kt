package com.template.app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.template.app.model.Data
import com.template.app.network.DataRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class NetworkRepositoryImpl(private val retrofit: DataRetrofit) : NetworkRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private val liveData = MutableLiveData<List<Data>>()

    private var data: List<Data> = ArrayList()
        set(value) {
            field = value
            liveData.postValue(value)
        }

    override fun getData(): LiveData<List<Data>> {
        launch {
            val newData = runCatching {
                val result = retrofit.getData()
                if (result.code() == 200) {
                    val c = result.body()
                    return@runCatching c as List<Data>
                } else {
                    return@runCatching null
                }
            }
            newData.getOrNull()?.let { data = it.sortedBy { item -> item.position } }
        }
        return liveData
    }

}
