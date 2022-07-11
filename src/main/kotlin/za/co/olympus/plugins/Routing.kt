package za.co.olympus.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import za.co.olympus.persistence.dao.dao

fun Application.configureRouting() {

    routing {
        get("/") {
            val result = dao.isLoginDetailsCorrect("3539211", "965484893393")

            if (result) {
                call.respondText(
                    text = result.toString(),
                    status = HttpStatusCode.OK
                )
                return@get
            }

            call.respond(status = HttpStatusCode.BadRequest, result.toString())
        }
        get("/dao") {
            val result = dao.isLoginDetailsCorrect("3539201", "965484893393")

            if (result) {
                call.respondText(
                    text = result.toString(),
                    status = HttpStatusCode.OK
                )
                return@get
            }

            call.respond(status = HttpStatusCode.BadRequest, result.toString())
        }
    }
}
