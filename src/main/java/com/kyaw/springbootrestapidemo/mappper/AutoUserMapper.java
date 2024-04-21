/**
 * @Author : Kyaw Zaw Htet
 * @Date : 4/21/2024
 * @Time : 6:24 AM
 * @Project_Name : spring-boot-rest-api-demo
 */
package com.kyaw.springbootrestapidemo.mappper;

import com.kyaw.springbootrestapidemo.dto.UserDto;
import com.kyaw.springbootrestapidemo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

     UserDto mapToUserDto(User user);

     User mapToUser(UserDto userDto);
}
