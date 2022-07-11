package za.co.olympus.persistence.dao

import za.co.olympus.model.Token

interface DaoFacade {
    suspend fun editEmployeeByInsertingPin(employeeNumber: String, pin: String): Token?
    suspend fun insertToken(): Boolean
    suspend fun verifyEmployeeNumberAndID(employeeNumber: String , employeeID: String): Boolean
    suspend fun verifyPin(auth: String, pin: String): Boolean
    suspend fun addEmployee(employeeNumber: String , employeeID: String): Boolean
}