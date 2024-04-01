package com.apollogix.managerskill.mapper;

import com.apollogix.managerskill.entity.Exam;
import com.apollogix.managerskill.response.ExamDetailResponse;
import com.apollogix.managerskill.response.ExamResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExamMapper {

    ExamMapper INSTANCE = Mappers.getMapper(ExamMapper.class);


    @Named(value = "useMe")
    ExamResponse toResponse(Exam exam);

    ExamDetailResponse toDetailResponse(Exam exam);

    @IterableMapping(qualifiedByName = "useMe")
    List<ExamResponse> listToResponse(List<Exam> exams);

}
