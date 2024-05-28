package com.ApiCourses.Courses.api.dto.response.used_responses;

import com.ApiCourses.Courses.domain.entities.Course;
import com.ApiCourses.Courses.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private String message;
    private UserEntity sender;
    private UserEntity receiver;
    private Course course;
}
