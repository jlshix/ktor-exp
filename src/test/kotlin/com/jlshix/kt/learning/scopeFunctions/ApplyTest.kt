package com.jlshix.kt.learning.scopeFunctions

import kotlin.test.Test
import kotlin.test.assertEquals

data class Person(
    var name: String,
    var age: Int = 0,
    var city: String = "",
)

class ApplyTest {
    @Test
    fun `apply this returns this`() {
        val adam = Person("Adam").apply {
            age = 32
            city = "London"
        }
        assertEquals(32, adam.age)
        assertEquals("London", adam.city)
    }
}