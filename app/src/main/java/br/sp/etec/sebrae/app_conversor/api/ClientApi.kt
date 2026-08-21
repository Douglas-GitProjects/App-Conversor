package br.sp.etec.sebrae.app_conversor.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientApi {

    private const val BASE_URL = "https://api.hgbrasil.com/"
    val api : FinanceApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(FinanceApi::class.java)
    }
}