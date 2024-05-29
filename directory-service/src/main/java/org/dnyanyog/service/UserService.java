package org.dnyanyog.service;

import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;

public interface UserService {

  public AddUserResponse addUser(AddUserRequest request);

  public AddUserResponse searchUser(Long userId);

  public AddUserResponse updateUser(Long userId, AddUserRequest request);

  public AddUserResponse deleteUser(Long userId);
}
