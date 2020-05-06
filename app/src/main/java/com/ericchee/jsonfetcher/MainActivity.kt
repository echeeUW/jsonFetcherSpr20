package com.ericchee.jsonfetcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ericchee.jsonfetcher.model.Email
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFetchJson.setOnClickListener {
            fetchDataWithGson()
        }

    }

    private fun fetchDataWithGson() {

        val gson = Gson()

        val email: Email = gson.fromJson(singleEmailJSONString, Email::class.java)

        email.from
        email.content
        email.id


        email.content?.let {
            Log.i(TAG, "Found an content of: $it")
        } ?: Toast.makeText(this, "Sorry invalid data", Toast.LENGTH_SHORT).show()

    }

    private fun fetchJson() {
        try {
            val jsonObject = JSONObject(emailOverviewJSONString)


            val title = jsonObject.getString("title")
            Log.i(TAG, "Title is: $title")

            val emailsJSON = jsonObject.getJSONArray("emails")

            for (index in 0 until emailsJSON.length()) {
                val emailJSON = emailsJSON.getJSONObject(index)
                val from = emailJSON.getString("from")

                Log.i(TAG, "Found email from: $from")
            }

        } catch (ex: Exception) {
            Toast.makeText(this, "Sorry invalid data", Toast.LENGTH_SHORT).show()
        }
    }

    private val singleEmailJSONString = """
        {
        	"id": 0,
        	"from": "seahawks@gmail.com",

        	"isImportant": true
        }
    """.trimIndent()


    private val emailOverviewJSONString = """
        {
        	"title": "Mailed It - App",

        	"emails": [
        		{
        			"id": 0,
        			"from": "seahawks@gmail.com",
        			"content": "Go Hawks!!! SEA!! HAWKSSS!!!! Go 12s! Legion of boom",
        			"isImportant": true
        		},
        		{
        			"id": 1,
        			"from": "49ers@hotmail.com",
        			"content": "Let's go Niners!!! Richard Sherman interception! Ay bay bay",
        			"isImportant": false
        		},
        		{
        			"id": 2,
        			"from": "patriots@aol.com",
        			"content": "We like flat footballs and spy cameras",
        			"isImportant": false
        		},
        		{
        			"id": 3,
        			"from": "tony@starkindustries.com",
        			"content": "I am Iron-Man and I love cheeseburgers. I love you 3000",
        			"isImportant": true
        		}
        	]
        }
    """.trimIndent()

    companion object {
        private val TAG = "echee"
    }
}
