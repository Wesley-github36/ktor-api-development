package za.co.olympus.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.config.*

class JwtConfig private constructor(
    config: ApplicationConfig
){
    private val algorithm = Algorithm.HMAC256(
        config.property("jwt.secret").getString()
    )
    val verifier = JWT
        .require(algorithm)
        .withIssuer(
            config.property("jwt.domain").getString()
        )
        .withAudience(
            config.property("jwt.audience").getString()
        )
        .build()

    val createToken: (employeeNumber: String) -> String = { employeeNumber ->
        JWT
            .create()
            .withIssuer(
                config.property("jwt.domain").getString()
            )
            .withAudience(
                config.property("jwt.audience").getString()
            )
            .withClaim(CLAIM, employeeNumber)
            .sign(algorithm)
    }


    companion object {
        const val CLAIM = "employeeNumber"
        lateinit var instance: JwtConfig
            private set


        val initializer: (config: ApplicationConfig) -> Unit = { config ->
            synchronized(this) {
                if (!this::instance.isInitialized)
                    instance = JwtConfig(config = config)
            }
        }
    }
}