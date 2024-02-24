package com.denisg.processamento.cart.application.controllers

import com.denisg.processamento.cart.application.usecases.ProcessarClientesUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ClienteController(private val processarClientesUseCase: ProcessarClientesUseCase) {

    @PostMapping("/processar-clientes")
    suspend fun processarClientes(@RequestParam filePath: String){
    processarClientesUseCase.execute(filePath)

    }
}