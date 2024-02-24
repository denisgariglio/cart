package com.denisg.processamento.cart.application.gateways

import com.denisg.processamento.cart.domain.Cliente
import java.io.File

class CsvReader {

    fun read(filePath: String): List<Cliente> {
        val clientes = mutableListOf<Cliente>()
        File(filePath).forEachLine { line ->
            val columns = line.split(",")
            val cliente = Cliente(columns[0], columns[1], columns[2], columns[3], "Ativo")
            clientes.add(cliente)
        }
        return clientes
    }

}