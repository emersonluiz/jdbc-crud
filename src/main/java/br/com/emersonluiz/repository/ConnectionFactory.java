package br.com.emersonluiz.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

    public static Connection createConnection(String base) throws Exception{
        
        String uri = "jdbc:postgresql://127.0.0.1:5432/" + base;
        String user = "joe";
        String pass = "teste";

        Connection connect = null;

        try{
            connect = DriverManager.getConnection(uri, user, pass);
            System.out.println("Conected!!!");
        }catch(SQLException e){
            System.out.println("Error");
            throw new Exception(e);
        }

        return connect;
    }
    
}