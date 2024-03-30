package com.apollogix.managerskill.mapper;

import com.apollogix.managerskill.entity.Exam;
import com.apollogix.managerskill.response.ExamResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExamMapper {

    ExamMapper INSTANCE = Mappers.getMapper( ExamMapper.class );

    ExamResponse toResponse(Exam exam);

    List<ExamResponse> listToResponse(List<Exam> exams);

}
