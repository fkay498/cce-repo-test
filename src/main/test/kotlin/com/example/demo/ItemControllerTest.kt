package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun `health is UP`() {
        mockMvc.get("/actuator/health").andExpect { status { isOk() } }
    }

    @Test
    fun `list returns seeded items`() {
        mockMvc.get("/api/v1/items").andExpect {
            status { isOk() }
            jsonPath("$[0].name") { value("Sample keyboard") }
        }
    }

    @Test
    fun `post creates an item`() {
        mockMvc.post("/api/v1/items") {
            contentType = MediaType.APPLICATION_JSON
            content = """{"name":"Test item","quantity":3}"""
        }.andExpect {
            status { isCreated() }
            jsonPath("$.name") { value("Test item") }
        }
    }

    @Test
    fun `post with invalid body returns 400`() {
        mockMvc.post("/api/v1/items") {
            contentType = MediaType.APPLICATION_JSON
            content = """{"name":"","quantity":0}"""
        }.andExpect { status { isBadRequest() } }
    }

    @Test
    fun `unknown id returns 404`() {
        mockMvc.get("/api/v1/items/9999").andExpect { status { isNotFound() } }
    }
}
