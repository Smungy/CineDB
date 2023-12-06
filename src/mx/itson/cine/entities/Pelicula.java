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
public class Pelicula {
   

    private int idPelicula;
    private String name;
    private String category;
    private String duration;
    
    public static List<Pelicula> getAll(String filtro){
        List<Pelicula> peliculas = new ArrayList();
        try{
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT idPelicula, name, category, duration FROM pelicula WHERE name LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                Pelicula p = new Pelicula();
                p.setIdPelicula(resultSet.getInt(1));
                p.setName(resultSet.getString(2));
                p.setCategory(resultSet.getString(3));
                p.setDuration(resultSet.getString(4));
                peliculas.add(p);
                
            }
                
        } catch(SQLException ex){
            System.err.println("Error: "+ex.getMessage());
        }
        return peliculas;    
    }
    
    //Agrega una pelicula
    public static boolean agregarPelicula(Pelicula pelicula) {
    try {
        Connection conexion = MySQLConnection.get();
        PreparedStatement statement = conexion.prepareStatement("INSERT INTO pelicula (name, category, duration) VALUES (?, ?, ?)");
        statement.setString(1, pelicula.getName());
        statement.setString(2, pelicula.getCategory());
        statement.setString(3, pelicula.getDuration());
        
        int filasAfectadas =statement.executeUpdate();
        
        return filasAfectadas > 0;
      
    } catch (SQLException ex) {
        System.err.println("Error al agregar pelicula: " + ex.getMessage());
         return false;
    }       
}
    //Elimina una pelicula  
    public static boolean eliminarPelicula(int idPelicula) {
    try {
        Connection conexion = MySQLConnection.get();
        PreparedStatement statement = conexion.prepareStatement("DELETE FROM pelicula WHERE idPelicula = ?");
        statement.setInt(1, idPelicula);
        
        int filasAfectadas = statement.executeUpdate();
        
        return filasAfectadas > 0;
        
    } catch (SQLException ex) {
        System.err.println("Error al eliminar pelicula: " + ex.getMessage());  
        return false;
    }
}
    //Actualiza una pelicula
    public static boolean actualizarPelicula(Pelicula pelicula) {
    try {
        Connection conexion = MySQLConnection.get();
        PreparedStatement statement = conexion.prepareStatement("UPDATE pelicula SET name = ?, category = ?, duration =? WHERE idPelicula = ?");
        statement.setString(1, pelicula.getName());
        statement.setString(2, pelicula.getCategory());
        statement.setString(3, pelicula.getDuration());
        statement.setInt(4, pelicula.getIdPelicula());

        int filasAfectadas = statement.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException ex) {
        System.err.println("Error al actualizar pelicula: " + ex.getMessage());
        return false;       
    }  
}
    public Pelicula() {
        
    }
    public Pelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    /**
     * @return the idPelicula
     */
    public int getIdPelicula() {
        return idPelicula;
    }

    /**
     * @param idPelicula the idPelicula to set
     */
    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
}

   