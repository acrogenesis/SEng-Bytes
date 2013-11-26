package entidades;
import java.util.Date;
import java.sql.*;
import java.io.*;

public class EditorJefe {

  Connection conn;
  Statement stmt;

  public EditorJefe(){
    try {
      String userName = "bluehats_SEng";
      String password = "amss1";
      String url = "jdbc:mysql://162.243.134.61/bluehats_SEng";
      Class.forName ("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection (url, userName, password);
      stmt = conn.createStatement();
    }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
  }

  public boolean existe(int editor){
    try {
      stmt.executeQuery ("SELECT idEditor FROM EditorJefe WHERE idEditor = " + editor);
       ResultSet rs = stmt.getResultSet();
       if (rs.next()) { //Va al primer registro si lo hay
        int idEditor = rs.getInt ("idEditor");
          rs.close(); 
          return( editor == idEditor );
       }else{ return false;}
    } catch (SQLException e) {}
      return false;
  }
  /*
  public void nominarJuez(int idJuez) {

  }

  public void altaJuez() {

  }

  public void generarArticulo(int idEdicion) {

  }*/
}

