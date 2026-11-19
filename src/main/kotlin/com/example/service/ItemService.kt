package com.example.demo.service

import com.example.demo.model.Item
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

class ItemNotFoundException(id: Long) : RuntimeException("Item $id not found")

@Service
class ItemService {
    private val items = ConcurrentHashMap<Long, Item>()
    private val sequence = AtomicLong(0)

    init {
        create("Sample keyboard", 10)
        create("Sample monitor", 5)
        create("Sample mouse", 25)
    }

    fun findAll(): List<Item> = items.values.sortedBy { it.id }

    fun findById(id: Long): Item = items[id] ?: throw ItemNotFoundException(id)

    fun create(name: String, quantity: Int): Item {
        val id = sequence.incrementAndGet()
        val item = Item(id = id, name = name, quantity = quantity, createdAt = Instant.now())
        items[id] = item
        return item
    }
}
