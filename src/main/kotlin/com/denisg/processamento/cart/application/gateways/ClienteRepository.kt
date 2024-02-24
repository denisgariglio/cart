package com.denisg.processamento.cart.application.gateways

interface ClienteRepository {
    suspend fun getStatus(clientId: String): String
    suspend fun atualizarStatus(clientId: String, novoStatus: String)
}