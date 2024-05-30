package com.ApiCourses.Courses.api.dto.response.custom_responses;

import com.ApiCourses.Courses.api.dto.response.used_responses.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseBasicResponseToSpecificResponse {
    private Long id;
    private String courseName;
    private String description;
    private UserBasicResponse user;
}
