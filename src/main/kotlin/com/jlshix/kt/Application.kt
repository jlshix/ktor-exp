package com.jlshix.kt

import io.ktor.application.*
import com.jlshix.kt.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.main() {
    configureRouting()
    configureHTTP()
    configureSerialization()
}
