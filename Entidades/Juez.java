package entidades;

import java.util.Date;
import java.sql.*;
import java.io.*;

public class Juez {
  Connection conn;
  Statement stmt;

  public Juez(){
    try {
       String userName = "root";
       String password = "amss";
       String url = "jdbc:mysql://162.243.146.245/SEng";
       Class.forName ("com.mysql.jdbc.Driver").newInstance();
       conn = DriverManager.getConnection (url, userName, password);
       stmt = conn.createStatement();
    }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
  }

  public boolean existe(int juez){
    try {
      stmt.executeQuery ("SELECT idJuez FROM Juez WHERE idJuez = " + juez);
      ResultSet rs = stmt.getResultSet();
      if (rs.next()) { //Va al primer registro si lo hay
        int idJuez = rs.getInt ("idJuez");
        rs.close(); 
        return( juez == idJuez );
      }else{ return false;}
    } catch (SQLException e) {}
      return false;
  }

  public void eliminar(int juez){
    try{
      stmt.executeQuery("DELETE FROM Juez WHERE idJuez = " + juez);
      ResultSet rs = stmt.getResultSet();
      rs.close();
    }catch (SQLException e) {}
  }
}