package entidades;
import java.sql.*;
import java.io.*;

public class ArticuloAutor {
  Connection conn;
  Statement stmt;

  public ArticuloAutor(){
    try {
      String userName = "bluehats_SEng";
      String password = "amss1";
      String url = "jdbc:mysql://162.243.134.61/bluehats_SEng";
      Class.forName ("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection (url, userName, password);
      stmt = conn.createStatement();
    }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
  }

  public void publicar(int articulo, int autor){
    try {
      String s = "INSERT INTO ArticuloAutor (idArticulo, idAutor)" +
                " VALUES ("+ articulo + " , " + autor + " )";
      System.out.println(s); 
      stmt.executeUpdate(s);
    }catch (Exception e) { System.out.println ("Cannot update database" + e ); }
  }
}
