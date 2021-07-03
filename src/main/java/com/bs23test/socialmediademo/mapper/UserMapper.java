package com.bs23test.socialmediademo.mapper;


import com.bs23test.socialmediademo.dto.UserDto;
import com.bs23test.socialmediademo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User>{

    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
