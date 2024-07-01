package edu.ar.data;

import edu.ar.model.Pelicula;

public class ConexionDAO {

  public static void main(String[] args) {
    
    //System.out.println(PeliculaDAO.obtener());

    //Extraer los datos del formulario , los guarda en un objeto Pelicula
    /* 
    Pelicula pelicula = new Pelicula(
      "Terminator 2: El juicio final", 
      "Un cyborg, idéntico al que fracasó en su intento de matar a Sarah Connor, debe proteger ahora a su hijo adolescente John de un cyborg más avanzado y poderoso.", 
      "Acción , Aventura, Ciencia Ficción", 
      86, 
      1991, 
      (byte)3,
      "James Cameron");

    System.out.println(PeliculaDAO.insertar(pelicula));
    */

    System.out.println(PeliculaDAO.seleccionarPorId(7));

  }

}
