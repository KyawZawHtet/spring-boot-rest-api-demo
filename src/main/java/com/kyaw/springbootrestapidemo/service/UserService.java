/**
 * @Author : Kyaw Zaw Htet
 * @Date : 4/15/2024
 * @Time : 7:52 PM
 * @Project_Name : spring-boot-rest-api-demo
 */
package com.kyaw.springbootrestapidemo.service;

import com.kyaw.springbootrestapidemo.dto.UserDto;
import com.kyaw.springbootrestapidemo.entity.User;

import java.util.List;

public interface UserService {
//    User createUser(User user);

    // Use DTO
    UserDto createUser(UserDto user);

//    User getUserById(Long userId);

    // Use DTO
    UserDto getUserById(Long userId);

//    List<User> getAllUsers();

    // Use DTO
    List<UserDto> getAllUsers();
//    User updateUser(User user);

    // Use DTO
    UserDto updateUser(UserDto user);
    void deleteUser(Long userId);
}
