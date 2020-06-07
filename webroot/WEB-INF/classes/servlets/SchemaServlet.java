package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import org.json.*;

import database.DBClient;
import json.JSONParserSchema;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Set;

public class SchemaServlet extends HttpServlet {

  JSONParserSchema json = new JSONParserSchema();
  DBClient dbClient = new DBClient();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException
  {
    response.setCharacterEncoding(UTF_8.name());
    response.setContentType("application/json; " + UTF_8.name());

    PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), UTF_8), true);
    StringBuilder sb = new StringBuilder();




    if (!request.getParameterNames().hasMoreElements()) {
      //return everything...
      System.out.println("Getting all schedules...");
      try {
        // now we have the id - grejen
        JSONArray jsonSchema = json.getDates();
        sb.append(jsonSchema.toString(2));
        out.println(sb.toString());
      } catch(JSONException e) {
        System.err.println("JSON error - in servletclass: " + e.getMessage());
      }
      return;
    }

    String idParam = request.getParameter("id");
    if (idParam == null) {
      out.println("{ \"error\" : \"missing required param id\"}");
      out.flush();
      return;
    }

    int id = 0;
    try {
      id = Integer.parseInt(idParam);
      System.out.println("Id = " + id);

      JSONArray times = json.getDatesForUser(id);
      try {
        sb.append(times.toString(2));
        out.println(sb.toString());
      } catch (JSONException e) {
        System.err.println("Wrong here " + e.getMessage());
      }

    } catch (NumberFormatException nfe) {
      out.println("{ \"error\" : \"missing required param id must be an integer\"}");
      out.flush();
      return;
    }
  }
}
