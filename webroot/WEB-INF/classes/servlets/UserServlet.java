package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import org.json.*;

import json.JSONParserUser;

public class UserServlet extends HttpServlet {

  JSONParserUser testJson = new JSONParserUser();
  private int id;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    response.setCharacterEncoding(UTF_8.name()); //Possible remove this. Don't really know what it does...
    response.setContentType("application/json; " + UTF_8.name());
    PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), UTF_8), true);
    StringBuilder sb = new StringBuilder();

    try {
      JSONArray jsonUsers = testJson.getJSONArray();
      sb.append(jsonUsers.toString(2));

    } catch (JSONException e) {
      System.err.println("JSONError" + e.getMessage());
    }

    out.println(sb.toString());
      
  }
}
