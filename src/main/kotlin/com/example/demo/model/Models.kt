package com.example.demo.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.Instant

@Schema(description = "A stock item")
data class Item(
    val id: Long,
    val name: String,
    val quantity: Int,
    val createdAt: Instant
)

@Schema(description = "Payload to create an item")
data class CreateItemRequest(
    @field:NotBlank(message = "name must not be blank")
    @field:Size(max = 100, message = "name must be at most 100 characters")
    @field:Schema(example = "Sample headset")
    val name: String,

    @field:Min(value = 1, message = "quantity must be at least 1")
    @field:Max(value = 10000, message = "quantity must be at most 10000")
    @field:Schema(example = "7")
    val quantity: Int
)

data class ErrorResponse(
    val timestamp: Instant,
    val status: Int,
    val error: String,
    val message: String,
    val path: String
)
