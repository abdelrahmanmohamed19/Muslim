package com.example.muslim.domain.usecase.quran

import NetworkUtils
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.muslim.R
import com.example.muslim.data.local.Quran
import com.example.muslim.data.local.QuranDataBase
import com.example.muslim.data.remote.dto.QuranResponse
import com.example.muslim.data.remote.dto.SuraList
import com.example.muslim.data.repository.QuranRepositoryImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetQuranUseCase () {

    private val repo = QuranRepositoryImpl()
    private val network = NetworkUtils

    suspend fun getQuran(id : Int, context : Context , snackBarAction : () -> Unit , view : View) : StateFlow<List<SuraList>> {
        val snackBar =  Snackbar.make(view,"No Internet Connection", Snackbar.LENGTH_INDEFINITE)
        val quranList = MutableStateFlow(emptyList<SuraList>())
        val database by lazy { Room.databaseBuilder(context, QuranDataBase::class.java , "myDatabase").build() }
        val dao = database.getDao()

        if (network.isInternetConnected()){
            val repoList = repo.getQuran(id).value
            if (repoList.isNotEmpty()){
                quranList.value = repo.getQuran(id).value
                dao.insertSura(Quran(id, QuranResponse(quranList.value)))
            }
            else {
                quranList.value = repoList
            }
        }

        else {
            val cachedData = dao.getSura(id)
            if (cachedData == null) {
                quranList.value = emptyList()
                val snackBarColor = ContextCompat.getColor(context,R.color.white)
                snackBar.setActionTextColor(snackBarColor).show()
                snackBar.setAction("Retry"){
                  snackBarAction()
                }
            }
            else {
                quranList.value = cachedData.suraList.result
            }
        }
        return quranList
    }
}