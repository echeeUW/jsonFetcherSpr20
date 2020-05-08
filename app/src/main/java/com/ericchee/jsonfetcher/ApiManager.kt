package com.ericchee.jsonfetcher

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ericchee.jsonfetcher.model.AllEmails
import com.ericchee.jsonfetcher.model.Email
import com.google.gson.Gson

class ApiManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun getEmail(onEmailReady: (Email) -> Unit) {
        val emailURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/email.json"

        val request = StringRequest(
            Request.Method.GET, emailURL,
            { response ->
                // Success
                val gson = Gson()
                val email = gson.fromJson(response, Email::class.java )

                onEmailReady(email)

            },
            {

            }
        )

        queue.add(request)
    }

    fun getListOfEmail(onEmailReady: (AllEmails) -> Unit, onError: (() -> Unit)? = null) {
        val emailURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/emails.json"

        val request = StringRequest(
            Request.Method.GET, emailURL,
            { response ->
                // Success
                val gson = Gson()
                val allEmails = gson.fromJson(response, AllEmails::class.java )

                onEmailReady(allEmails)

            },
            {
                onError?.invoke()
            }
        )

        queue.add(request)
    }

}