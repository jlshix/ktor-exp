package com.jlshix.kt.learning.scopeFunctions

import kotlin.test.Test
import kotlin.test.assertEquals

class WithTest {
    private val numbers = listOf("one", "two", "three")

    @Test
    fun `with is not an ext function`() {
        with(numbers) {
            println("'with' is called with argument $this")
            println("it contains $size elements")
            assertEquals(3, size)
            assertEquals(3, this.size)
            assertEquals("three", last())
        }
    }

    @Test
    fun `with returns lambda result`() {
        val firstAndLast = with(numbers) {
            "first: ${first()}; last: ${last()}"
        }
        assertEquals("first: one; last: three", firstAndLast)
    }

}