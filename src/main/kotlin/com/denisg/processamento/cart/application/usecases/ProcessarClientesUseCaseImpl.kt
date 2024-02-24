package com.denisg.processamento.cart.application.usecases

import com.denisg.processamento.cart.application.gateways.ClienteRepository
import com.denisg.processamento.cart.application.gateways.CsvReader
import com.denisg.processamento.cart.application.gateways.SnsGateway
import com.denisg.processamento.cart.domain.Cliente

class ProcessarClientesUseCaseImpl(
        private val csvReader: CsvReader,
        private val clienteRepository: ClienteRepository,
        private val snsGateway: SnsGateway
): ProcessarClientesUseCase  {


    override suspend fun execute(filePath: String) {
        val clientes = csvReader.read(filePath)
        clientes.forEach { cliente ->
                processarCliente(cliente)
        }
    }

    private suspend fun processarCliente(cliente: Cliente) {
        val status = clienteRepository.getStatus(cliente.id)
        if(status == "Ativo") {
            cliente.status = "Bloqueado"
            clienteRepository.atualizarStatus(cliente.id, cliente.status)
            snsGateway.enviarNotificacao(cliente)
        }

    }


}