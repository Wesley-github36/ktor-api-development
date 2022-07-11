package za.co.olympus.model

data class Employee (
    val id: Int,
    val employeeNumber: String,
    val employeeID: String,
    val pin: String? = null,
    val auth: String? = null
)
