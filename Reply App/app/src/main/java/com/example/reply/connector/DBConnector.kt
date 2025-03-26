package com.example.reply.connector

import com.example.reply.data.Account
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

private class DBConnector {

    private val url: String = "jdbc:mariadb://192.168.0.106:3306/blazor"
    private val username: String = "remote_admin"
    private val password: String = "@HP24"

    fun getConnection(): Connection? {
        try {
            Class.forName("org.mariadb.jdbc.Driver")
            return DriverManager.getConnection(url, username, password)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

    fun createTable(tableName: String, dataModel: Class<*>): Boolean {
        val connection = DriverManager.getConnection(
            url,
            username,
            password
        ) ?: return false

        val statement = getConnection()?.createStatement()
        val createTableQuery = buildCreateTableQuery(tableName, dataModel)
        try {
            statement?.execute(createTableQuery)
            return true
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            closeConnection(connection)
        }
        return false
    }


    private fun buildCreateTableQuery(tableName: String, dataModel: Class<*>): String {
        val fields = dataModel.declaredFields.map { field ->
            val fieldName = field.name
            val fieldType = getSqlType(field.type)
            "$fieldName $fieldType"
        }
        val query = "CREATE TABLE IF NOT EXISTS $tableName (" + fields.joinToString(", ") + ");"
        return query
    }

    private fun getSqlType(kotlinType: Class<*>): String {
        return when (kotlinType) {
            Int::class.javaPrimitiveType, Long::class.javaPrimitiveType -> "INT"
            String::class.java -> "VARCHAR(255)"
            // Add mappings for other data types as needed
            else -> throw IllegalArgumentException("Unsupported data type: $kotlinType")
        }
    }

    private fun closeConnection(connection: Connection) {
        try {
            connection.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}

fun main() {
    DBConnector().createTable(
        "Accounts",
        Account::class.java
    )

}