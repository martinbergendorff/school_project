package json;

import java.sql.*;
import org.json.*;
import java.util.List;
import java.util.ArrayList;

import database.DBClient;
import schedule.Schedule;
import user.User;

public class JSONParserSchema {

  DBClient dbClient = new DBClient();
  JSONArray jsonDates = new JSONArray();
  List<Schedule>dates;

  public JSONArray getDates() {
    dates = new ArrayList<Schedule>();
    try {
      ResultSet rs = dbClient.getAllTimes();
      while (rs.next()) {
        User user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
        System.out.println(user);
        Schedule date = new Schedule(user, rs.getString("day"));
        dates.add(date);
      }
    } catch(SQLException e) {
      System.err.println("oh no");
    }
    JSONArray times = parseDates(dates);
    return times;
  }


  public JSONArray getDatesForUser(int id) {
    ResultSet rs = dbClient.getTimesById(id);
    List<Schedule> schedForUser = new ArrayList<>();

    try {
      while (rs.next()) {
        User user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
        Schedule date = new Schedule(user, rs.getString("day"));
        schedForUser.add(date);
      }
    } catch (SQLException e){
      System.err.println("HELVETE!!!");
    }
    JSONArray times = parseDates(schedForUser);
    return times;
  }

  public JSONArray parseDates(List<Schedule> times) {
    JSONArray jsonTimeArray = new JSONArray();
    for(Schedule date : times) {
      try {
        JSONObject jsonTimeObject = new JSONObject();
        jsonTimeObject.put("first_name", date.getUser().firstName());
        jsonTimeObject.put("last_name", date.getUser().lastName());
        jsonTimeObject.put("date", date.getDate());

        jsonTimeArray.put(jsonTimeObject);
      } catch(JSONException e) {
        System.err.println("something is wrong here");
      }
    }
    return jsonTimeArray;
  }

  // public JSONArray getJSONDates() {
  //   List<Schedule> datesToParse = retrieveDatesFromDatabase();
  //   for (Schedule date : datesToParse) {
  //     // System.out.println(date);
  //     try {
  //       JSONObject jsonDate = new JSONObject();
  //       jsonDate.put("name", date.getUser().firstName() + " " + date.getUser().lastName());
  //       jsonDate.put("date", date.getDate());
  //       jsonDates.put(jsonDate);
  //       // System.out.println(jsonDates.toString());
  //     } catch(JSONException e) {
  //         System.err.println("JSON error: " + e.getMessage());
  //     }
  //   }
  //   return jsonDates;
  // }
}
