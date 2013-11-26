package entidades;
import java.util.Date;
import java.sql.*;
import java.io.*;

public class Articulo {

Connection conn;
Statement stmt;

  public Articulo(){
        try {
          String userName = "root";
          String password = "amss";
          String url = "jdbc:mysql://162.243.146.245/SEng";
          Class.forName ("com.mysql.jdbc.Driver").newInstance();
          conn = DriverManager.getConnection (url, userName, password);
          stmt = conn.createStatement();
        }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
  }

  public boolean existe(int articulo){
        try {
           stmt.executeQuery ("SELECT idArticulo FROM Articulo WHERE idArticulo = " + articulo);
           ResultSet rs = stmt.getResultSet();
           if (rs.next()) { //Va al primer registro si lo hay
              int idArticulo = rs.getInt ("idArticulo");
              rs.close(); 
              return( articulo == idArticulo );
           }else{ return false;}
        } catch (SQLException e) {}
        return false;
  }

  public boolean publicar(int idArticulo, String titulo, String fecha, int idEdicion, String contenido){
    try {
      stmt.executeQuery ("SELECT idArticulo FROM Articulo WHERE idArticulo =" + idArticulo);
      ResultSet rs = stmt.getResultSet();
      int idArt = 0;
      if(rs.next()){
        idArt = rs.getInt ("idArticulo");
        rs.close();
      }
      if(idArt == 0){
        String s = "INSERT INTO Articulo (idArticulo, titulo, fecha, idEdicion, contenido)" +
                " VALUES ("+ idArticulo + " , '" + titulo + "', '" + fecha + "', " + idEdicion + ",'" + contenido + "' )";
          System.out.println(s);
          stmt.executeUpdate(s);
        return true;
      }
      else{return false;}
      }catch (Exception e) { System.out.println ("Cannot update database" + e ); }
      return false;
  }

  public String getContenido(int articulo){
    try{
      stmt.executeQuery("SELECT contenido FROM Articulo WHERE idArticulo = "+ articulo);
      ResultSet rs = stmt.getResultSet();
      if(rs.next()){
        String contenido = rs.getString("contenido");
        rs.close();
        return contenido;
      }else{return null;}
    } catch(SQLException e){}
    return null;
  }

  public boolean sePuedeAprobar(int articulo){
    try{
      stmt.executeQuery ("SELECT COUNT(*) AS hay, idArticulo, idEditorJefe FROM Articulo WHERE idArticulo =" + articulo + " AND idEditorJefe IS NULL");
      ResultSet rs = stmt.getResultSet();
      if (rs.next()){
        int hay = rs.getInt("hay");
        rs.close();
        return(hay==1);
      }
    }catch (Exception e){}
    return false;
  }

  public void aprobarArt(int articulo, int editorJefe){
    try{
      String s = "UPDATE Articulo SET idEditorJefe ="+editorJefe+ " WHERE idArticulo = "+articulo;
      System.out.println(s); 
      stmt.executeUpdate(s);
    }catch (Exception e) { System.out.println ("Cannot update database" + e ); } 
  }

  /*int idArticulo;
  String titulo;
  Date fecha;
  boolean aprobado;
  int idEdicion;
  int idEditorJefe;
  String contenido;*/



  /*public void consultarArticulo(int idArticulo) {

  }

  public void votarArticulo(int idJuez) {

  }

  public void consultarArticulosVotados() {

  }

  public void aprobarArticulo(int idEdiJefe) {

  }

  public void consultarRes() {

  }*/
}
