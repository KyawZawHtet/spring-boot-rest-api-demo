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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
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
    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("/create-user")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
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
    @Operation(
            summary = "Get User By ID REST API",
            description = "Get User By ID REST API is used to get a single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
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
    @Operation(
            summary = "Get All User REST API",
            description = "Get All User REST API is used to get a all the users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
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
    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a particular user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user) {
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Build Delete User REST API
    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
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
