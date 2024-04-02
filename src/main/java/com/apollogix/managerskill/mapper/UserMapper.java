package com.apollogix.managerskill.mapper;

import com.apollogix.managerskill.entity.MUser;
import com.apollogix.managerskill.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserResponse toResponse(MUser user);

}
