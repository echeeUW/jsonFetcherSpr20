package com.ericchee.jsonfetcher

import com.ericchee.jsonfetcher.model.AllEmails
import com.ericchee.jsonfetcher.model.Email
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager(private val mailService: MailService) {

    fun getEmail(onEmailReady: (Email) -> Unit, onError: (() -> Unit)? = null) {
        mailService.email().enqueue(object : Callback<Email>{
            override fun onResponse(call: Call<Email>, response: Response<Email>) {
                val email = response.body()
                if (email != null) {
                    onEmailReady(email)
                } else {
                    onError?.invoke()
                }
            }

            override fun onFailure(call: Call<Email>, t: Throwable) {
                onError?.invoke()
            }
        })

    }

    fun getListOfEmail(onEmailReady: (AllEmails) -> Unit, onError: (() -> Unit)? = null) {

        mailService.allEmails().enqueue(object : Callback<AllEmails> {
            override fun onResponse(call: Call<AllEmails>, response: Response<AllEmails>) {
                val allEmails = response.body()
                if (allEmails != null) {
                    onEmailReady(allEmails)
                } else {
                    onError?.invoke()
                }
            }

            override fun onFailure(call: Call<AllEmails>, t: Throwable) {
                onError?.invoke()
            }
        })
    }

}