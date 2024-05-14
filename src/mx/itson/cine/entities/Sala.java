/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.cine.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.cine.persistence.MySQLConnection;

/**
 *
 * @author Jesus Alan
 */
public class Sala {

    
    private int idSala;
    private int numberSala;
    private int capacity;
    private String stateSala;
    
    
    public static List<Sala> getAll(String filtro){
        List<Sala> salas = new ArrayList();
        try{
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT idSala, numberSala, capacity, stateSala FROM sala WHERE numberSala LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                Sala s = new Sala();
                s.setIdSala(resultSet.getInt(1));
                s.setNumberSala(resultSet.getInt(2));
                s.setCapacity(resultSet.getInt(3));
                s.setStateSala(resultSet.getString(4));
                salas.add(s);
                
            }
                
        } catch(SQLException ex){
            System.err.println("Error: "+ex.getMessage());
        }
        return salas;    
    }
    
    //Agrega una sala
    public static boolean agregarSala(Sala sala) {
    try {
        Connection conexion = MySQLConnection.get();
        PreparedStatement statement = conexion.prepareStatement("INSERT INTO sala (numberSala, capacity, stateSala) VALUES (?, ?, ?)");
        statement.setInt(1, sala.getNumberSala());
        statement.setInt(2, sala.getCapacity());
        statement.setString(3, sala.getStateSala());
        
        int filasAfectadas =statement.executeUpdate();
        
        return filasAfectadas > 0;
      
    } catch (SQLException ex) {
        System.err.println("Error al agregar sala: " + ex.getMessage());
         return false;
    }       
}
    //Elimina una sala  
    public static boolean eliminarSala(int idSala) {
    try {
        Connection conexion = MySQLConnection.get();
        PreparedStatement statement = conexion.prepareStatement("DELETE FROM sala WHERE idSala = ?");
        statement.setInt(1, idSala);
        
        int filasAfectadas = statement.executeUpdate();
        
        return filasAfectadas > 0;
        
    } catch (SQLException ex) {
        System.err.println("Error al eliminar sala: " + ex.getMessage());  
        return false;
    }
}
    //Actualiza una sala
    public static boolean actualizarSala(Sala sala) {
    try {
        Connection conexion = MySQLConnection.get();
        PreparedStatement statement = conexion.prepareStatement("UPDATE sala SET numberSala = ?, capacity = ?, stateSala =? WHERE idSala = ?");
        statement.setInt(1, sala.getNumberSala());
        statement.setInt(2, sala.getCapacity());
        statement.setString(3, sala.getStateSala());
        statement.setInt(4, sala.getIdSala());

        int filasAfectadas = statement.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException ex) {
        System.err.println("Error al actualizar Sala: " + ex.getMessage());
        return false;       
    }  
}
    
      public Sala() {
        
    }
        public Sala(int idSala) {
        this.idSala = idSala;
    }

    /**
     * @return the idSala
     */
    public int getIdSala() {
        return idSala;
    }

    /**
     * @param idSala the idSala to set
     */
    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    /**
     * @return the numberSala
     */
    public int getNumberSala() {
        return numberSala;
    }

    /**
     * @param numberSala the numberSala to set
     */
    public void setNumberSala(int numberSala) {
        this.numberSala = numberSala;
    }
    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
  

    /**
     * @return the stateSala
     */
    public String getStateSala() {
        return stateSala;
    }

    /**
     * @param stateSala the stateSala to set
     */
    public void setStateSala(String stateSala) {
        this.stateSala = stateSala;
    }
    
    
    
}
