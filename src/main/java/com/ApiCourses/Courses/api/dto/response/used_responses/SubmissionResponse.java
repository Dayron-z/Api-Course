package com.ApiCourses.Courses.api.dto.response.used_responses;

import com.ApiCourses.Courses.api.dto.response.used_responses.SubjectResponse;
import com.ApiCourses.Courses.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponse {
    private Long id;
    private String content;
    private Double grade;


    /*Por revisar*/
    private SubjectResponse subject;

    /*Por revisar*/
    private UserEntity user;
}
