package com.ericchee.jsonfetcher

import android.app.Application
import com.ericchee.jsonfetcher.model.AllEmails
import com.ericchee.jsonfetcher.model.Email
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class CoolHttpApp: Application() {

    lateinit var apiManager: ApiManager
    private lateinit var mailService: MailService

    override fun onCreate() {
        super.onCreate()

        // Init Retrofit + MailService
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create()) // this will automatically apply Gson conversion :)
            .build()
        mailService = retrofit.create(MailService::class.java)

        // Load managers
        apiManager = ApiManager(mailService)
    }
}

interface MailService {

    @GET("echeeUW/codesnippets/master/emails.json")
    fun allEmails(): Call<AllEmails>

    @GET("echeeUW/codesnippets/master/email.json")
    fun email(): Call<Email>
}