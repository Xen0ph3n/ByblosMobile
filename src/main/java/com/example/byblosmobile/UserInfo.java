package com.example.byblosmobile;

import androidx.annotation.NonNull;

public class UserInfo {
  protected String userName, firstName, lastName, email, userType, password;

  public UserInfo() {
    this.firstName = null;
    this.lastName = null;
    this.email = null;
    this.userType = null;
    this.password = null;
  }

  public UserInfo(String userName, String firstName, String lastName, String email, String password, String userType) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.userType = userType;
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getUserType() {
    return userType;
  }

  public void copy(UserInfo other) {
    this.firstName = other.firstName;
    this.lastName = other.lastName;
    this.email = other.email;
    this.password = other.password;
    this.userName = other.userName;
    this.userType = other.userType;
  }

  @NonNull
  @Override
  public String toString() {
    return "User {" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", userType='" + userType + '\'' +
            '}';
  }
}
