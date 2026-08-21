package br.sp.etec.sebrae.app_conversor.model

data class Moeda(
    val name : String,
    val buy : Double,
    val sell : Double,
    val variation : Double
)
