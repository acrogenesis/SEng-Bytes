package controles;

import entidades.*;
import entidades.Autor;
import entidades.Usuario;
import entidades.Editorial;

public class ControlAltaAutor {

  Autor autor;
  Usuario usuario;
  Editorial editorial;

  public ControlAltaAutor() {
    autor = new Autor();
    usuario = new Usuario();
    editorial = new Editorial();
  }

  public boolean Alta(int idUsuario,int idEditorial){
    if(usuario.existe(idUsuario) && editorial.existe(idEditorial) && !autor.existe(idUsuario)){
      autor.crear(idUsuario,idEditorial);
      return true;
    }
    else return false;
  }
}
