package com.denisg.processamento.cart.application.gateways

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import software.amazon.awssdk.services.dynamodb.model.*

import kotlinx.coroutines.future.await
import java.util.concurrent.CompletableFuture

class DynamoClienteRepository: ClienteRepository {

    private val dynamoClient = DynamoDbAsyncClient.builder()
            .credentialsProvider(DefaultCredentialsProvider.create())
            .region(Region.SA_EAST_1)
            .build()

    override suspend fun getStatus(clientId: String): String {
        val getRequest = GetItemRequest.builder()
                .tableName("Clientes")
                .key(mapOf("Id" to AttributeValue.builder().s(clientId).build()))
                .projectionExpression("Status")
                .build()

        val responseFuture: CompletableFuture<GetItemResponse> = dynamoClient.getItem(getRequest)
        val response: GetItemResponse = responseFuture.await()

        return response.item()["Status"]?.s() ?: "Desconhecido"
    }

    override suspend fun atualizarStatus(clientId: String, novoStatus: String) {
        val updateRequest = UpdateItemRequest.builder()
                .tableName("Clientes")
                .key(mapOf("Id" to AttributeValue.builder().s(clientId).build()))
                .updateExpression("SET Status = :status")
                .expressionAttributeValues(mapOf(":status" to AttributeValue.builder().s(novoStatus).build()))
                .build()

        dynamoClient.updateItem(updateRequest).await()
    }
}