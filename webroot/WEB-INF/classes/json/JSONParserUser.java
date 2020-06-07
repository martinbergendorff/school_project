package json;

import org.json.*;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import database.DBClient;
import user.User;

public class JSONParserUser {
  DBClient dbClient = new DBClient();
  //JSONObject jsonUsers = new JSONObject();
  JSONArray jsonUsers = new JSONArray();

    // Gets the result from the query performed in the DBClient class.
    public List<User> getAllUsers() {
      List<User> users = new ArrayList<>();
      try {
        ResultSet rs = dbClient.getAllUsersQuery();
        while(rs.next()) {
          User user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
          //System.out.println(user.firstName());
          users.add(user);
        }
      } catch(SQLException e ) {
        System.err.println("Something went wrong while retrieving users: " + e.getMessage());
      }
      return users;
    }

    public JSONArray getJSONArray() {
      List<User> users  = getAllUsers();
      try {
        for(User user : users ) {
            // System.out.println(user);
            JSONObject jsonUser = new JSONObject();

            jsonUser.put("firstname", user.firstName());
            jsonUser.put("lastname", user.lastName());
            jsonUser.put("email", user.email());
            jsonUsers.put(jsonUser);
            // jsonUsers.accumulate("Users" , jsonUser);
         }
        } catch (JSONException e) {
        System.err.println("Json error: " + e.getMessage());
       }
       return jsonUsers;
    }

    public void getUser(int idOne) {

      ResultSet rs = dbClient.getUserById(idOne);
      try {
        while (rs.next()) {
          User user = new User(rs.getString("first_name"),
          rs.getString("last_name"),
          rs.getString("email"));
        }
      } catch(SQLException e) {
        System.err.println("Error fetching user from db.");
      }
    }
}
