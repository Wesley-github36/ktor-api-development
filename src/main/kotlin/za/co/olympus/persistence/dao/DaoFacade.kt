package za.co.olympus.persistence.dao

interface DaoFacade {
    suspend fun editEmployeeByInsertingPin(employeeNumber: String, pin: String): String?
    suspend fun insertToken(): Boolean
    suspend fun verifyEmployeeNumberAndID(employeeNumber: String , employeeID: String): Boolean
    suspend fun verifyPin(auth: String, pin: String): Boolean
    suspend fun addEmployee(employeeNumber: String , employeeID: String): Boolean
}