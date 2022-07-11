package za.co.olympus.persistence.dto

import org.jetbrains.exposed.sql.Table

object EmployeeDTO : Table() {
    val id = integer("id").autoIncrement()
    val employeeNumber = varchar("employeeNumber", 1024)
    val employeeID = varchar("employeeID", 1024)
    val pin = varchar("pin", 1024).nullable()
    val auth = varchar("auth", 1024).nullable()

    override val primaryKey = PrimaryKey(employeeID)
}