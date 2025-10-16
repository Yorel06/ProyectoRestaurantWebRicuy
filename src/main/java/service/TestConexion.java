
package service;

import java.sql.Connection;
import java.sql.DriverManager;


public class TestConexion {

    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=bd_rikuy;encrypt=false;";;
        String user = "sa";
        String password = "sql";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection cn = DriverManager.getConnection(url, user, password)) {
                System.out.println("Conexión exitosa a SQL Server");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
