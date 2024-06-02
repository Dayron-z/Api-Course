package com.ApiCourses.Courses.api.dto.response.custom_responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseBasicResponseToUserResponse {
    private Long id;
    private String courseName;
    private String description;
    private UserBasicResponse instructor;
    private List<LessonBasicResponse> lessons;
}



