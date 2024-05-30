package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

@Component
public class LoginRequest {

  @NotNull(message = "Mobile number is mandatory")
  public Long mobileNumber;

  @NotNull(message = "Password is mandatory")
  public String password;

  public Long getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(Long mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
