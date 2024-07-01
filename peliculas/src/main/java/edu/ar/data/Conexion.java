package edu.ar.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

//Clase que se encarga de conectarse con la BD
public class Conexion {

  private final static String JDBC_URL =
   "jdbc:mysql://localhost:3306/peliculas_web?useSSL=false&useTimeZone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
  private final static String JDBC_USER = "root";
  //En JDBC_PASS ingresan su contraseña.
  private final static String JDBC_PASS = "";

  public static DataSource getDataSource() {
    BasicDataSource ds = new BasicDataSource();
    ds.setUrl(JDBC_URL);
    ds.setUsername(JDBC_USER);
    ds.setPassword(JDBC_PASS);
    ds.setInitialSize(100);
    return ds;
  }

  public static Connection getConexion() throws SQLException {

    //bloque try catch
    //En la seccion try ejecuto el código deseado
    //En la sección catch atrapo la excepción o error si sucede.
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      //agrupar excepciones en un solo catch
    } catch (ClassNotFoundException | ArithmeticException ex) {
      ex.printStackTrace(System.out);
    }
    return getDataSource().getConnection();
  }

  public static void close (ResultSet rs) throws SQLException {
    rs.close();
  }

  public static void close (Statement st) throws SQLException {
    st.close();
  }

  public static void close (Connection cn) throws SQLException {
    cn.close();
  }

}
