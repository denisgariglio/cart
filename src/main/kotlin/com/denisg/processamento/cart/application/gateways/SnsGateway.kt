package com.denisg.processamento.cart.application.gateways

import com.denisg.processamento.cart.domain.Cliente
import kotlinx.coroutines.future.await
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sns.SnsAsyncClient
import software.amazon.awssdk.services.sns.model.PublishRequest

class SnsGateway {

    private val snsClient = SnsAsyncClient.builder()
            .credentialsProvider(DefaultCredentialsProvider.create())
            .region(Region.SA_EAST_1)
            .build()

    suspend fun enviarNotificacao(cliente: Cliente) {
        val message = "O status do cliente ${cliente.nome} foi alterado para Bloqueado."
        val request = PublishRequest.builder()
                .topicArn("vai buscar de properties")
                .message(message)
                .build()

        snsClient.publish(request).await()

    }
}