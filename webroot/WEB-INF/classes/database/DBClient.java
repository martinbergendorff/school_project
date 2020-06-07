package database;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DBClient {

  private static Connection con;

  static {
      try {
        Class.forName("org.sqlite.JDBC");
      } catch (ClassNotFoundException e) {
        System.err.println("Connection error: DB - " + e.getMessage());
      }
  }

  public DBClient() {
    try {
      con = DriverManager.getConnection("jdbc:sqlite:schedule_it.db");
      System.out.println("Connected! :) ");
    } catch(SQLException sqle) {
      System.err.println("Could not connect to database: " + sqle.getMessage());
    }
  }

  public ResultSet getAllUsersQuery(){
    ResultSet rs = null;
    try {
      Statement stm = con.createStatement();
      rs = stm.executeQuery("SELECT * FROM User;");

    } catch(SQLException sqle) {
      System.err.println("Could not connect to database: " + sqle.getMessage());
    }
    return rs;
  }

  public ResultSet getUserById(int id) {
    ResultSet rs = null;
    try {
      Statement stm = con.createStatement();
      rs = stm.executeQuery("Select * from user where id = " + id);

    } catch (SQLException e) {
      System.err.println("Error when executing query");
    }
    return rs;
  }

  public ResultSet getAllTimes() {
    ResultSet rs = null;
    try {
      Statement stm = con.createStatement();
      rs = stm.executeQuery("SELECT first_name, last_name, email, day FROM user JOIN schedule ON user.id = user_id;");
    } catch (SQLException e) {
      System.err.println("Error executing query.");
    }
    return rs;
  }

  public ResultSet getTimesById(int id) {
    ResultSet rs = null;
    try {
      Statement stm = con.createStatement();
      rs = stm.executeQuery("SELECT first_name, last_name, email, day FROM user JOIN schedule ON user.id = user_id WHERE id = " + id + ";");
    } catch(SQLException e){
        System.err.println("Error executing query: select by id.");
    }
    return rs;
  }
}
