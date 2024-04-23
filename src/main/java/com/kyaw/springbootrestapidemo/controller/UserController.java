/**
 * @Author : Kyaw Zaw Htet
 * @Date : 4/15/2024
 * @Time : 7:51 PM
 * @Project_Name : spring-boot-rest-api-demo
 */
package com.kyaw.springbootrestapidemo.controller;

import com.kyaw.springbootrestapidemo.dto.UserDto;
import com.kyaw.springbootrestapidemo.entity.User;
import com.kyaw.springbootrestapidemo.exception.ErrorDetails;
import com.kyaw.springbootrestapidemo.exception.ResourceNotFoundException;
import com.kyaw.springbootrestapidemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private UserService userService;

    // Build Create User REST API
//    @PostMapping("/create-user")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User createUser = userService.createUser(user);
//        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
//    }

    // Use DTO
    @PostMapping("/create-user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto createUser = userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    // Build Get User By ID REST API
    // http://localhost:8080/api/v1/users/1
//    @GetMapping("{id}")
//    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
//        User user = userService.getUserById(userId);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    // Use DTO
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Build Get All User REST API
    // http://localhost:8080/api/v1/users
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    // Use DTO
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Build Update User REST API
    // http://localhost:8080/api/v1/users/1
//    @PutMapping("{id}")
//    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User user) {
//        user.setId(userId);
//        User updatedUser = userService.updateUser(user);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }

    // Use DTO
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto user) {
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Successfully Deleted!!!", HttpStatus.OK);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
//        ErrorDetails errorDetails = new ErrorDetails(
//               LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER NOT FOUND"
//        );
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
}
