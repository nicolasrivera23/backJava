package edu.ar.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static edu.ar.data.Conexion.close;
import static edu.ar.data.Conexion.getConexion;

import edu.ar.model.Pelicula;

public class PeliculaDAO {


  private static final String SQL_SELECT = "SELECT * FROM movie";
  private static final String SQL_INSERT = 
  "INSERT INTO movie (nombre, descripcion, genero, calificacion, anio, estrellas, director) values (?,?,?,?,?,?,?);";
  private static final String SQL_SELECT_BY_ID = "SELECT * FROM movie WHERE id = ?";

  public static List<Pelicula> obtener() {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Pelicula pelicula = null;
    List<Pelicula> peliculas = new ArrayList<>();

    try {
      conn = getConexion();
      ps = conn.prepareStatement(SQL_SELECT);
      rs = ps.executeQuery();

      //Itero los resultados del ResultSet
      while(rs.next()){
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String descripcion = rs.getString("descripcion");
        String genero = rs.getString("genero");
        int calificacion = rs.getInt("calificacion");
        int anio = rs.getInt("anio");
        byte estrellas = rs.getByte("estrellas");
        String director = rs.getString("director");

        pelicula = new Pelicula(id, nombre, descripcion, genero, calificacion, anio, estrellas, director);

        //agregar la pelicula en nuestro listado de pelis
        peliculas.add(pelicula);

      }
    } catch (Exception e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        close(rs);
        close(ps);
        close(conn);
      } catch(Exception e) {
        e.printStackTrace(System.out);
      }
    }
    return peliculas;
  }

  //Insertar
  public static int insertar(Pelicula peli) {
    Connection conn = null;
    PreparedStatement ps = null;
    int registros = 0;

    try {
      conn = getConexion();
      ps = conn.prepareStatement(SQL_INSERT);
      ps.setString(1, peli.getNombre());
      ps.setString(2, peli.getDescripcion());
      ps.setString(3, peli.getGenero());
      ps.setInt(4, peli.getCalificacion());
      ps.setInt(5, peli.getAnio());
      ps.setByte(6, peli.getEstrellas());
      ps.setString(7, peli.getDirector());
      registros = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        close(ps);
        close(conn);
      } catch(SQLException ex) {
        ex.printStackTrace(System.out);
      }
    }
    return registros;
  }

  //Seleccionar una Película usando el ID
   //Seleccionar por ID
   public static Pelicula seleccionarPorId(int id) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Pelicula pelicula = null;

    try {
      conn = getConexion();
      ps = conn.prepareStatement(SQL_SELECT_BY_ID);
      ps.setInt(1, id);
      rs = ps.executeQuery();

      while (rs.next()) {
        int idPeli = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String descripcion = rs.getString("descripcion");
        String genero = rs.getString("genero");
        int calificacion = rs.getInt("calificacion");
        int anio = rs.getInt("anio");
        byte estrellas = rs.getByte("estrellas");
        String director = rs.getString("director");

        pelicula = new Pelicula(idPeli, nombre, descripcion, genero, calificacion, anio, estrellas, director);
      }
    } catch(SQLException e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        close(rs);
        close(ps);
        close(conn);
      } catch(SQLException e) {
        e.printStackTrace(System.out);
      }
    }
    return pelicula;
  }

}
