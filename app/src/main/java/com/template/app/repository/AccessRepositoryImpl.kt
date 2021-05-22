package com.template.app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.template.app.BuildConfig
import com.template.app.coroutines.await
import com.template.app.lang.tryOrNull
import com.template.app.network.AccessGeoRetrofit
import com.template.app.network.AccessRetrofit
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AccessRepositoryImpl(
    val access: AccessRetrofit,
    val geo: AccessGeoRetrofit
): AccessRepository, CoroutineScope {

    override fun getAccess(): LiveData<Access>{
        val data = MutableLiveData<Access>()
        launch {

            val db = Firebase.firestore
            val snapshot = runCatching {
                db.collection("config").document(BuildConfig.APPLICATION_ID).get().await()
            }

            if (snapshot.isFailure){
                data.postValue(Access(false))
                return@launch
            }

            val url = snapshot.getOrNull()?.getString("url")
            val key = snapshot.getOrNull()?.getString("key") ?: "at_2ol2kldlOS1LOMrXJlARq1J5xEmIg"

            val ip = tryOrNull { access.getIp().body() }
            if (ip == null) {
                data.postValue(Access(false))
                return@launch
            }
            val geo = tryOrNull { geo.getGeo(key, ip).body() }
            if (geo?.location?.country != "RU"){
                data.postValue(Access(false))
                return@launch
            }
            data.postValue(Access(true, url))
        }
        return data
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

}
