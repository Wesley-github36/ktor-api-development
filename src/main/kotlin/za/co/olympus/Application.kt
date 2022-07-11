package za.co.olympus

import io.ktor.server.application.*
import za.co.olympus.persistence.config.DatabaseFactory
import za.co.olympus.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.init(this.environment.config)
    configureRouting()
    configureSecurity()
    configureMonitoring()
    configureSerialization()
}


/**
 * mangoUser: Olympus
 * mangaPassword: 8BRWYPQbr99GeNgr
 * mangoUrl: mongodb+srv://Olympus:<password>@olympuscluster.x3kgp.mongodb.net/?retryWrites=true&w=majority
 */