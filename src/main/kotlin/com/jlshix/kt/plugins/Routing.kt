package com.jlshix.kt.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.http.content.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

@Location("/locations/{name}")
class MLocation(val name: String, val skip: Int = 0, val limit: Int = 20) {
    fun description(): String = "MLocation(name=$name, skip=$skip, limit=$limit)"
}

class PathNotExistException: Exception()


fun Application.configureRouting() {
    install(Locations) {}

    routing {
        get("/") {
            call.respondText("ktor-exp")
        }
        get("/exceptions/404") {
            throw PathNotExistException()
        }
        get<MLocation> {
            call.respondText(it.description())
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
        install(StatusPages) {
            exception<PathNotExistException> { cause ->
                call.respond(
                    message = mapOf("error" to cause.message),
                    status = HttpStatusCode.NotFound
                )
            }
            exception<NotFoundException> { cause ->
                call.respond(
                    message = mapOf("error" to cause.message),
                    status = HttpStatusCode.NotFound
                )
            }
        }
    }
}

