/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.cine.entities;

import java.sql.ResultSet;
import mx.itson.cine.persistence.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesus Alan
 */
public class Funcion {

    private int idFuncion;
    private Pelicula pelicula;
    private Sala sala;
    private String stateFuncion;
    private Date dateFuncion;
    private Time startFuncion;
       
    public static List<Funcion> getAll(String filtro) {
    List<Funcion> funciones = new ArrayList<>();

    try {
        Connection connection = MySQLConnection.get();

        String query = "SELECT idFuncion, idSala, idPelicula, stateFuncion, dateFuncion, startFuncion FROM funcion WHERE stateFuncion LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + filtro + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Funcion funcion = new Funcion();
                funcion.setIdFuncion(resultSet.getInt("idFuncion"));
                // Crear instancias de Sala y Pelicula y establecer sus IDs
                Sala sala = new Sala();
                sala.setIdSala(resultSet.getInt("idSala"));
                funcion.setSala(sala);
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(resultSet.getInt("idPelicula"));
                funcion.setPelicula(pelicula);
                funcion.setStateFuncion(resultSet.getString("stateFuncion"));
                funcion.setDateFuncion(resultSet.getDate("dateFuncion"));
                funcion.setStartFuncion(resultSet.getTime("startFuncion"));
                           
                funciones.add(funcion);
            }
        }

    } catch (SQLException ex) {
        System.err.println("Ocurrió un error: " + ex.getMessage());
    }

    return funciones;
}
 
    
  public static boolean agregarFuncion(Funcion funcion) {
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO funcion (idPelicula, idSala, stateFuncion, dateFuncion, startFuncion) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, funcion.getPelicula().getIdPelicula());
            statement.setInt(2, funcion.getSala().getIdSala());
            statement.setString(3, funcion.getStateFuncion());
            statement.setDate(4, funcion.getDateFuncion());
            statement.setTime(5, funcion.getStartFuncion());

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException ex) {
            System.err.println("Error al agregar función: " + ex.getMessage());
            return false;
        }
    }

    // Método para eliminar una función
    public static boolean eliminarFuncion(int idFuncion) {
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM funcion WHERE idFuncion = ?");
            statement.setInt(1, idFuncion);

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException ex) {
            System.err.println("Error al eliminar función: " + ex.getMessage());
            return false;
        }
    }

    // Método para actualizar una función
    public static boolean actualizarFuncion(Funcion funcion) {
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("UPDATE funcion SET idSala= ?, idPelicula = ?, stateFuncion = ?, dateFuncion = ?, startFuncion = ? WHERE idFuncion = ?");
            statement.setInt(1, funcion.getSala().getIdSala());
            statement.setInt(2, funcion.getPelicula().getIdPelicula());
            statement.setString(3, funcion.getStateFuncion());
            statement.setDate(4, funcion.getDateFuncion());
            statement.setTime(5, funcion.getStartFuncion());
            statement.setInt(6, funcion.getIdFuncion());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException ex) {
            System.err.println("Error al actualizar función: " + ex.getMessage());
            return false;
        }
    }


    

    /**
     * @return the idFuncion
     */
    public int getIdFuncion() {
        return idFuncion;
    }

    /**
     * @param idFuncion the idFuncion to set
     */
    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }

    /**
     * @return the pelicula
     */
    public Pelicula getPelicula() {
        return pelicula;
    }

    /**
     * @param pelicula the pelicula to set
     */
    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    /**
     * @return the sala
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * @param sala the sala to set
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     * @return the stateFuncion
     */
    public String getStateFuncion() {
        return stateFuncion;
    }

    /**
     * @param stateFuncion the stateFuncion to set
     */
    public void setStateFuncion(String stateFuncion) {
        this.stateFuncion = stateFuncion;
    }
/**
     * @return the dateFuncion
     */
    public Date getDateFuncion() {
        return dateFuncion;
    }

    /**
     * @param dateFuncion the dateFuncion to set
     */
    public void setDateFuncion(Date dateFuncion) {
        this.dateFuncion = dateFuncion;
    }

    /**
     * @return the startFuncion
     */
    public Time getStartFuncion() {
        return startFuncion;
    }

    /**
     * @param startFuncion the startFuncion to set
     */
    public void setStartFuncion(Time startFuncion) {
        this.startFuncion = startFuncion;
    }
    
}
    
