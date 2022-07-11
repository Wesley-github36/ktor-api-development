package za.co.olympus.security

import io.ktor.server.auth.*

@JvmInline
value class EmployeeNumberPrincipal(val employeeID: String)
    : Principal
