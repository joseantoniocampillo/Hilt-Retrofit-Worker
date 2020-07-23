package dev.campi.hiltretrofitworker

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.campi.hiltretrofitworker.di.ApiService

class GetPostsWorker @WorkerInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val apiService: ApiService
) : CoroutineWorker(context, workerParameters) {


    override suspend fun doWork(): Result {
        Log.d("qqq", "GetPostsWorker.doWork working...")
        return try {
            val posts = apiService.getPosts()
            posts.forEach {post ->
                Log.d("qqq", "GetPostsWorker.doWork  post.title: ${post.title}")
            }
            Log.d("qqq", "GetPostsWorker.doWork  success")
            Result.success()
        } catch (e: Exception) {
            Log.d("qqq", "!! exception.message:${e.message}")
            Result.failure()
        }

    }
}