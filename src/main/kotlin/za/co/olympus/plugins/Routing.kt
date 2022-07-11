package za.co.olympus.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.util.*
import za.co.olympus.persistence.dao.dao

fun Application.configureRouting() {

    routing {
        get("/") {
            val r1 = dao.addEmployee("3539211", "9388736526723")
            val r2 = dao.addEmployee("1234611", "1176253498019")
            val r3 = dao.addEmployee("8765290", "2278765364109")

            if (r1 and r2 and r3)
                return@get call.respond("DONE")

            return@get call.respond("Error")
        }

        get("/login") {

            val parameters = call.receiveParameters()
            val employeeNumber = parameters.getOrFail("employeeNumber")
            val employeeID = parameters.getOrFail("employeeID")

            val result = dao.verifyEmployeeNumberAndID(
                employeeNumber,
                employeeID
            )


            if (result) {
                call.respondText(text = result.toString(), status = HttpStatusCode.OK)
                return@get
            }

            call.respondText(text = result.toString(), status = HttpStatusCode.NotFound)
        }

        get("/addPin") {
            val parameters = call.receiveParameters()
            val employeeNumber = parameters.getOrFail("employeeNumber")
            val pin = parameters.getOrFail("pin")

            val token = dao.editEmployeeByInsertingPin(
                employeeNumber,
                pin
            )

            if (token == null) {
                call.respondText(status = HttpStatusCode.BadGateway, text = "null")
                return@get
            }

            call.respondText(status = HttpStatusCode.OK, text = token.toString())

        }
    }
}
