package com.template.app.ui.main.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.template.app.model.Data
import com.template.app.repository.NetworkRepository

class PlayersViewModel(
    val repo: NetworkRepository
): ViewModel() {

    fun getData(): LiveData<List<Data>> = repo.getData()
}
