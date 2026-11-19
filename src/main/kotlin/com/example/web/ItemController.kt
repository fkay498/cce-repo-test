package com.example.demo.web

import com.example.demo.model.CreateItemRequest
import com.example.demo.model.Item
import com.example.demo.service.ItemService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/items")
@Tag(name = "Items", description = "Sample in-memory items")
class ItemController(private val service: ItemService) {

    @GetMapping
    @Operation(summary = "List all items")
    fun list(): List<Item> = service.findAll()

    @GetMapping("/{id}")
    @Operation(summary = "Get one item by id")
    fun get(@PathVariable id: Long): Item = service.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an item")
    fun create(@Valid @RequestBody request: CreateItemRequest): Item =
        service.create(request.name, request.quantity)
}
