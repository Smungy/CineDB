/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.cine.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Jesus Espericueta
 */
public class MySQLConnection {
    
    public static Connection get(){
        Connection connection = null;
        try{
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinedb?user=yourusert&password=yourpassword");
            
        } catch (Exception ex){
            System.err.println("Error: "+ ex.getMessage());
        }
        return connection;
    }     
            
}
