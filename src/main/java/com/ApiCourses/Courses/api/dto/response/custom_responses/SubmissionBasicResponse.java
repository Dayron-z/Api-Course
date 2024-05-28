package com.ApiCourses.Courses.api.dto.response.custom_responses;

import com.ApiCourses.Courses.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionBasicResponse {
    private Long id;
    private String content;
    private Double grade;
    private UserEntity user;
}
