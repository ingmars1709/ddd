package com.infosupport.poc.db;

import org.hsqldb.jdbc.JDBCDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection;

    public Database(String databaseName) throws SQLException {
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setDatabase("jdbc:hsqldb:file:" + databaseName);
        connection = dataSource.getConnection("sa", "");
    }

    public void shutdown() throws SQLException {
        if(connection != null) {
            try (Statement st = connection.createStatement()) {
                /*
                 * db writes out to files and performs clean shuts down otherwise there
                 * will be an unclean shutdown when program ends
                 */
                st.execute("SHUTDOWN");
            }
        }
    }

    private void executeQuery(final String query) throws SQLException {
        if(connection != null) {
            try (Statement st = connection.createStatement()) {
                st.executeQuery(query);
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        Database dddb = new Database("dddb");

        dropTablePaymentInstruction(dddb);
        dropTableOrderingAccount(dddb);

        createOrderingAccountTable(dddb);
        createPaymentInstructionTable(dddb);

        insertOrderingAccounts(dddb);
        insertPaymentinstruciton(dddb);

        dddb.shutdown();
    }

    private static void insertPaymentinstruciton(Database dddb) throws SQLException {
        dddb.executeQuery("INSERT INTO paymentinstruction(id, orderingAccountId, forwardDateTime) VALUES (1, 1, NOW())");
    }

    private static void dropTableOrderingAccount(Database dddb) throws SQLException {
        dddb.executeQuery("DROP TABLE IF EXISTS orderingaccount");
    }

    private static void dropTablePaymentInstruction(Database dddb) throws SQLException {
        dddb.executeQuery("DROP TABLE IF EXISTS paymentinstruction");
    }

    private static void createPaymentInstructionTable(Database dddb) throws SQLException {
        dddb.executeQuery("CREATE TABLE IF NOT EXISTS paymentinstruction(id numeric(10,0) IDENTITY NOT NULL, orderingAccountId numeric(10,0) NOT NULL, forwardDateTime datetime NOT NULL)");
    }

    private static void createOrderingAccountTable(Database dddb) throws SQLException {
        final String queryCreateOrderingAccountTable = "CREATE TABLE IF NOT EXISTS orderingaccount(id numeric(10,0), orderingAccountIdentification varchar(255) NOT NULL, PRIMARY KEY(id))";
        dddb.executeQuery(queryCreateOrderingAccountTable);
    }

    private static void insertOrderingAccounts(Database dddb) throws SQLException {
        dddb.executeQuery("DELETE FROM orderingaccount");
        dddb.executeQuery("INSERT INTO orderingaccount(id, orderingAccountIdentification) VALUES (1, 'NL91ABNA0441710441')");
        dddb.executeQuery("INSERT INTO orderingaccount(id, orderingAccountIdentification) VALUES (2, 'NL91ABNA0524307784')");
    }
}