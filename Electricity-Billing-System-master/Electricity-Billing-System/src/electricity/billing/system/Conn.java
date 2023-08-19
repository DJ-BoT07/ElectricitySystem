package electricity.billing.system;

import java.sql.*;

public class Conn {

    Connection c;
    Statement s;

    // Constructor
    Conn() {
        try {
            // Establish a connection to the MySQL database
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "password");

            // Create a statement object for executing SQL queries
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
 * the Conn class provides a convenient way to establish a connection to a MySQL
 * database using the JDBC (Java Database Connectivity) API. The Connection and
 * Statement objects are essential for performing database operations such as
 * executing queries and retrieving results. This class encapsulates the
 * database connection setup and provides a foundation for database interactions
 * in the electricity billing system application.
 */