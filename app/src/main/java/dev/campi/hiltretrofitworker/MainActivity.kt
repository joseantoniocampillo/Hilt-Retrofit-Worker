package dev.campi.hiltretrofitworker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.AndroidEntryPoint
import dev.campi.hiltretrofitworker.di.ApiService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciaTrabajo()
    }

    private fun iniciaTrabajo() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val request = OneTimeWorkRequestBuilder<GetPostsWorker>()
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance(applicationContext).enqueue(request)
    }
}