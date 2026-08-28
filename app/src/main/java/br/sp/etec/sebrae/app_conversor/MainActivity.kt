package br.sp.etec.sebrae.app_conversor

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.sp.etec.sebrae.app_conversor.api.ClientApi
import br.sp.etec.sebrae.app_conversor.model.FinanceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var cotacaoDollar : Double = 0.0
    var cotacaoEuro : Double = 0.0
    var cotacaoPeso : Double = 0.0
    var cotacaoLibra : Double = 0.0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val moedas = arrayOf("Dólar", "Euro", "Peso Argentino", "Libra")
        val spMoeda = findViewById<Spinner>(R.id.spMoedas)

        val moedasAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, moedas)

        spMoeda.adapter = moedasAdapter
        carregarCotacoes()

        val btnConverte = findViewById<Button>(R.id.btnConverte)
        btnConverte.setOnClickListener {
            val valor = findViewById<EditText>(R.id.txtValor).text.toString().toDouble()
            val itemSelecionado = spMoeda.selectedItem.toString()
            val valorCotacao = when(itemSelecionado){
                "Dólar" -> valor * cotacaoDollar
                "Euro" -> valor * cotacaoEuro
                "Peso Argentino" -> valor * cotacaoPeso
                "Libra" -> valor * cotacaoLibra
                else -> {
                    0.0
                }
            }
            val resultado = findViewById<TextView>(R.id.textViewResultado)
            resultado.text = "Valor em %.2f".format(valorCotacao)
        }
    }

    private fun carregarCotacoes() {
        ClientApi.api.getCotacoes().enqueue(object : Callback<FinanceResponse> {
            override fun onResponse(
                p0: Call<FinanceResponse?>,
                response: Response<FinanceResponse?>
            ) {
                val moedas = response.body()?.results?.currencies
                cotacaoDollar = moedas?.USD?.buy ?: 0.0
                cotacaoEuro = moedas?.USD?.buy ?: 0.0
                cotacaoPeso = moedas?.USD?.buy ?: 0.0
                cotacaoLibra = moedas?.USD?.buy ?: 0.0
            }

            override fun onFailure(p0: Call<FinanceResponse?>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}