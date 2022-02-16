package com.jlshix.kt.learning.scopeFunctions

import kotlin.test.Test
import kotlin.test.assertEquals

class TakeTest {
    @Test
    fun `takeIf like filter`() {
        val number = 42
        val result = number.takeIf { it % 2 == 0 }?.times(2)?.plus(2)
        assertEquals(86, result)
    }

    @Test
    fun `take null in chain`() {
        val number = 43
        val result = number.takeIf { it % 2 == 0 }?.times(2)?.plus(2)
        assertEquals(null, result)
    }

    @Test
    fun `takeUnless like filterNot`() {
        val number = 42
        val result = number.takeUnless { it % 2 != 0 }?.times(2)?.plus(2)
        assertEquals(86, result)
    }
}