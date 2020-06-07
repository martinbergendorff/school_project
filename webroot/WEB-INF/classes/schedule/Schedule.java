package schedule;

import user.User;

public class Schedule {
  private User user;
  private String date;

  public Schedule(User user, String date) {
    this.user = user;
    this.date = date;
  }

  public String getDate() {
    return date;
  }

  public User getUser() {
    return user;
  }

  @Override
  public String toString(){
    return user + " " + date;
  }

}
