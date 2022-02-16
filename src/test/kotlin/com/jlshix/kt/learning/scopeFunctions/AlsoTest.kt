package com.jlshix.kt.learning.scopeFunctions

import kotlin.test.Test
import kotlin.test.assertEquals

class AlsoTest {
    @Test
    fun `also it returns this`() {
        val numbers = mutableListOf("one", "two", "three")
        numbers.also { println("before added: $numbers") }.add("four")
        assertEquals(mutableListOf("one", "two", "three", "four"), numbers)
    }
}