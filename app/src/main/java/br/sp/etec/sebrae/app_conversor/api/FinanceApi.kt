package br.sp.etec.sebrae.app_conversor.api

import br.sp.etec.sebrae.app_conversor.model.FinanceResponse
import retrofit2.Call
import retrofit2.http.GET

interface FinanceApi {
    @GET ("finance?key=d18b57f7")
    fun getCotacoes() : Call<FinanceResponse>
}