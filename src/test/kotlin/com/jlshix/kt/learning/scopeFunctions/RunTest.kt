package com.jlshix.kt.learning.scopeFunctions

import kotlin.test.Test
import kotlin.test.assertEquals

class MultiPortService(var url: String, var port: Int) {
    fun prepareRequest(): String = "Default request"
    fun query(request: String): String = "Result for query '$request'"
}

class RunTest {
    private val service = MultiPortService("https://example.kotlinlang.org", 80)

    @Test
    fun `run this returns lambda result`() {
        val result = service.run {
            port = 8080
            query(prepareRequest() + " to port $port")
        }
        assertEquals("Result for query 'Default request to port 8080'", result)
        assertEquals(8080, service.port)
    }

    @Test
    fun `same code written with let()`() {
        val result = service.let {
            it.port = 8081
            it.query(it.prepareRequest() + " to port ${it.port}")
        }
        assertEquals("Result for query 'Default request to port 8081'", result)
        assertEquals(8081, service.port)
    }

    @Test
    fun `run as non-extension function`() {
        val result = run {
            println("inside non-extension function run()")
            service.port = 8082
            service.query(service.prepareRequest() + " to port ${service.port}")
        }
        assertEquals("Result for query 'Default request to port 8082'", result)
        assertEquals(8082, service.port)
    }
}