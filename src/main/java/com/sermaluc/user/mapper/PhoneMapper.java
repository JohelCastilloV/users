package com.sermaluc.user.mapper;

import com.sermaluc.user.controller.dto.PhoneDTO;
import com.sermaluc.user.model.Phone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
     Phone mapTo(PhoneDTO phoneDTO);
}
