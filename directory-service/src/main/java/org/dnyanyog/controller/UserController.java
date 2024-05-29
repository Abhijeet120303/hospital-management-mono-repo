package org.dnyanyog.controller;

import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UserController {

  @Autowired UserService service;

  @PostMapping(
      path = "/api/v1/directory/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AddUserResponse addPatient(@Valid @RequestBody AddUserRequest request) {
    return service.addUser(request);
  }

  @GetMapping(path = "/api/v1/directory/search/{userId}")
  public AddUserResponse searchPatient(@PathVariable Long userId) {
    return service.searchUser(userId);
  }

  @PostMapping(
      path = "/api/v1/patient/edit/{userId}",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AddUserResponse updatePatient(
      @PathVariable Long userId, @RequestBody AddUserRequest request) {
    return service.updateUser(userId, request);
  }

  @DeleteMapping(
      path = "/api/v1/directory/delete/{userId}",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AddUserResponse deletePatient(@PathVariable Long userId) {
    return service.deleteUser(userId);
  }
}
