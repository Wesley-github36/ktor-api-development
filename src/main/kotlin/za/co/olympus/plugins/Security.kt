package za.co.olympus.plugins

import io.ktor.server.auth.*
import io.ktor.util.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import za.co.olympus.security.EmployeeNumberPrincipal
import za.co.olympus.security.JwtConfig

fun Application.configureSecurity() {

    authentication {
        JwtConfig.initializer(this@configureSecurity.environment.config)

        jwt {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.CLAIM).asString()

                if (claim != null)
                    EmployeeNumberPrincipal(claim)
                else
                    null
            }
        }
    }

}
