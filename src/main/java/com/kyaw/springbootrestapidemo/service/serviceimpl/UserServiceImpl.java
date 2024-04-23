/**
 * @Author : Kyaw Zaw Htet
 * @Date : 4/15/2024
 * @Time : 7:52 PM
 * @Project_Name : spring-boot-rest-api-demo
 */
package com.kyaw.springbootrestapidemo.service.serviceimpl;

import com.kyaw.springbootrestapidemo.dto.UserDto;
import com.kyaw.springbootrestapidemo.entity.User;
import com.kyaw.springbootrestapidemo.exception.EmailAlreadyExitsException;
import com.kyaw.springbootrestapidemo.exception.ResourceNotFoundException;
import com.kyaw.springbootrestapidemo.mappper.AutoUserMapper;
import com.kyaw.springbootrestapidemo.repository.UserRepository;
import com.kyaw.springbootrestapidemo.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

//    @Override
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

    // Use DTO
    @Override
    public UserDto createUser(UserDto userDto) {

        // Convert UserDto into Use JPA Entity
//        User user = new User(
//                userDto.getId(),
//                userDto.getFirstName(),
//                userDto.getLastName(),
//                userDto.getEmail()
//        );

//        User user = UserMapper.mapToUser(userDto);

        //Use ModelMapper
//        User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExitsException("Email Already Exists for User");
        }

        // Use MapStruct
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        // Convert User JPA Entity to UserDto
//        UserDto savedUserDto = new UserDto(
//                savedUser.getId(),
//                savedUser.getFirstName(),
//                savedUser.getLastName(),
//                savedUser.getEmail()
//        );

//        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        // Use ModelMapper
//        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        // Use MapStruct
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
//        User user = optionalUser.get();
//        return UserMapper.mapToUserDto(user);

        // Use ModelMapper
//        return modelMapper.map(user, UserDto.class);

        // Use MapStruct
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

        // Use ModelMapper
//        return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        // Use MapStruct
        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {

        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updated = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updated);

        // Use ModelMapper
//        return modelMapper.map(updated, UserDto.class);

        // Use MapStruct
        return AutoUserMapper.MAPPER.mapToUserDto(updated);
    }

    @Override
    public void deleteUser(Long userId) {

        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        userRepository.deleteById(userId);
    }
}
