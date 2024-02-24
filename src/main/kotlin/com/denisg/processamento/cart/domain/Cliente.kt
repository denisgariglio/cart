package com.denisg.processamento.cart.domain

data class Cliente(
        val id: String,
        val nome: String,
        val nroCartao: String,
        val nomeCliente: String,
        var status: String
)
