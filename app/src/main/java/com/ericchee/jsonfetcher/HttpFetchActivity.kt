package com.ericchee.jsonfetcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.RequestQueue
import kotlinx.android.synthetic.main.activity_http_fetch.*
import java.net.URL

class HttpFetchActivity : AppCompatActivity() {
    private val TAG = "echee"

    private lateinit var queue: RequestQueue
    lateinit var apiManager: ApiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_fetch)

        apiManager = (application as CoolHttpApp).apiManager

        btnFetch.setOnClickListener {
//            runOnBackgroundTask()
            fetchEmailWithVolley()

            Log.i(TAG, "update view 1")
        }
    }

    private fun fetchEmailWithVolley() {
        apiManager.getEmail { email ->
            Log.i(TAG, "Email received from $email")
        }

        Log.i(TAG, "update view 1")
        Log.i(TAG, "update view 2")
        Log.i(TAG, "update view 3")
        Log.i(TAG, "update view 4")
        Log.i(TAG, "update view 5")
        Log.i(TAG, "update view 6")
    }



    private fun makeHTTPRequest() {
        val emailJSONString = URL("https://raw.githubusercontent.com/echeeUW/codesnippets/master/email.json").readText()
        Log.i(TAG, "$emailJSONString")

        runOnUiThread {
            Toast.makeText(this, "Complete!", Toast.LENGTH_SHORT).show()
            progressBar.visibility = View.GONE
        }


    }

    private fun longNetworkRequest() {
        repeat(100) { count ->
            Thread.sleep(100)
            Log.i(TAG, "$count")
        }

        runOnUiThread {
            Toast.makeText(this, "Complete!", Toast.LENGTH_SHORT).show()
            progressBar.visibility = View.GONE
        }
    }

    private fun runOnBackgroundTask(){
        val task = Runnable {
            longNetworkRequest()
            makeHTTPRequest()
        }

        val backgroundThread = Thread(task)
        backgroundThread.start()
    }
}
