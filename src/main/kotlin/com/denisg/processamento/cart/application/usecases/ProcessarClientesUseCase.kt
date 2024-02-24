package com.denisg.processamento.cart.application.usecases

interface ProcessarClientesUseCase {
    suspend fun execute(filePath: String)
}