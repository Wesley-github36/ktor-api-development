package za.co.olympus.persistence.dao

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import za.co.olympus.model.Employee
import za.co.olympus.model.Token
import za.co.olympus.persistence.config.DatabaseFactory.dbQuery
import za.co.olympus.persistence.dto.EmployeeDTO

class DaoFacadeImpl : DaoFacade {
    private fun resultRowToEmployee(row: ResultRow) = Employee(
        id = row[EmployeeDTO.id],
        employeeNumber = row[EmployeeDTO.employeeNumber],
        employeeID = row[EmployeeDTO.employeeID],
        pin = row[EmployeeDTO.pin],
        auth = row[EmployeeDTO.auth],
    )

    /**
     * On initial setup
     */
    override suspend fun verifyEmployeeNumberAndID (
        employeeNumber: String,
        employeeID: String
    ): Boolean = dbQuery {

        val result = EmployeeDTO
            .select {
                EmployeeDTO.employeeNumber.eq(employeeNumber) and
                        EmployeeDTO.employeeID.eq(employeeID)
            }
            .map(::resultRowToEmployee)
            .singleOrNull()

        return@dbQuery result is Employee
    }


    override suspend fun editEmployeeByInsertingPin(
        employeeNumber: String ,
        pin: String
    ): Token? = dbQuery {

        val result = EmployeeDTO.update({ EmployeeDTO.employeeNumber.eq(employeeNumber) }) {
            it[EmployeeDTO.pin] = pin
        } > 0

        return@dbQuery null

    }
    override suspend fun insertToken(): Boolean {
        TODO()
    }
    override suspend fun verifyPin(
        auth: String,
        pin: String
    ): Boolean  = dbQuery {

        val result = EmployeeDTO
            .select {
                EmployeeDTO.auth.eq(auth) and
                        EmployeeDTO.pin.eq(pin)
            }
            .map(::resultRowToEmployee)
            .singleOrNull()

        return@dbQuery result is Employee
    }

    /**
     * Add Employee to database
     */
    override suspend fun addEmployee(
        employeeNumber: String,
        employeeID: String
    ): Boolean = dbQuery {

        val insertStatement = EmployeeDTO.insert {
            it[EmployeeDTO.employeeID] = employeeID
            it[EmployeeDTO.employeeNumber] = employeeNumber
        }
        val result = insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToEmployee)

        return@dbQuery result is Employee
    }
}

val dao: DaoFacade = DaoFacadeImpl().apply {
    runBlocking {
        addEmployee(
            "3539211",
            "8802111275088"
        )
    }
}