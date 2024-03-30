package com.apollogix.managerskill.mapper;

import com.apollogix.managerskill.entity.Question;
import com.apollogix.managerskill.response.QuestionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper( QuestionMapper.class );

    QuestionResponse toResponse(Question question);

    List<QuestionResponse> listToResponse(List<Question> questions);

}
