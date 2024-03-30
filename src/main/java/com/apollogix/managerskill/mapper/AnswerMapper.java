package com.apollogix.managerskill.mapper;

import com.apollogix.managerskill.entity.Answer;
import com.apollogix.managerskill.response.AnswerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnswerMapper {

    AnswerMapper INSTANCE = Mappers.getMapper( AnswerMapper.class );

    AnswerResponse toResponse(Answer answer);

    List<AnswerResponse> listToResponse(List<Answer> answers);

}
