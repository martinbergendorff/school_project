package user;

public class User {
  private String firstName;
  private String lastName;
  private String email;

  public User(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String firstName() {
    return firstName;
  }

  public String lastName() {
    return lastName;
  }

  public String email() {
    return email;
  }

  public String toString(){
    return firstName + " : " + lastName + " : " + email;
  }
}
