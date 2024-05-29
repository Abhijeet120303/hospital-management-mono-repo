package org.dnyanyog.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
@Component
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "user_name", nullable = false)
  private String userName;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "mobile_number", nullable = false, unique = true)
  private Long mobileNumber;

  @Column(name = "role", nullable = false)
  private String role;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "status", nullable = false)
  private String status;

  @Column private String aes_Key;

  public static Users getInstance() {
    return new Users();
  }

  public Long getUserId() {
    return userId;
  }

  public Users setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public Users setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public Users setEmail(String email) {
    this.email = email;
    return this;
  }

  public Long getMobileNumber() {
    return mobileNumber;
  }

  public Users setMobileNumber(Long mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public String getRole() {
    return role;
  }

  public Users setRole(String role) {
    this.role = role;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Users setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public Users setStatus(String status) {
    this.status = status;
    return this;
  }

  public String getAes_Key() {
    return aes_Key;
  }

  public Users setAes_Key(String aes_Key) {
    this.aes_Key = aes_Key;
    return this;
  }
}