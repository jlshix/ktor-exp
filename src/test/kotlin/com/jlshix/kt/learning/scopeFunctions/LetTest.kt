package com.jlshix.kt.learning.scopeFunctions

import kotlin.test.Test
import kotlin.test.assertEquals

class LetTest {
    private val numbers = mutableListOf("one", "two", "three", "four", "five")

    @Test
    fun `let it returns lambda result`() {
        val rv = numbers.map { it.length }.filter { it > 3 }.let {
            println(it)
            it
        }
        assertEquals(listOf(5, 4, 4), rv)
    }

    @Test
    fun `let method reference`() {
        val rv = numbers.map { it.length }.filter { it > 3 }.let(::println)
        assertEquals(Unit, rv)
    }

    @Test
    fun `let safe call`() {
        val str: String? = "Hello"
        val length = str?.let {
            println("let() called on $it")
            it.length
        }
        assertEquals(5, length)
    }

    @Test
    fun `let lambda argument name`() {
        val modifiedFirstItem = numbers.first().let { firstItem ->
            println("first item of list is $firstItem")
            if (firstItem.length > 5) firstItem else "!$firstItem!"
        }.uppercase()
        assertEquals("!ONE!", modifiedFirstItem)
    }
}