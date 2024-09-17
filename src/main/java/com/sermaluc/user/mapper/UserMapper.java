package com.sermaluc.user.mapper;

import com.sermaluc.user.controller.dto.UserRequestDTO;
import com.sermaluc.user.controller.dto.UserResponseDTO;
import com.sermaluc.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {PhoneMapper.class}, componentModel = "spring")
public interface UserMapper {
   User mapToEntity(UserRequestDTO userRequestDTO);
   @Mapping(target = "isActive", source = "status")
   @Mapping(target = "token", source = "token.token")
   @Mapping(target = "id", expression = "java(user.getUuid().toString())")
   UserResponseDTO mapToDTO(User user);
}
