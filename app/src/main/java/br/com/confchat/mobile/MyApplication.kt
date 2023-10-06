package br.com.confchat.mobile

import android.app.Application
import androidx.room.Room
import br.com.confchat.mobile.data.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
}