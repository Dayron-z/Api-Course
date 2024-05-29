package com.ApiCourses.Courses.api.dto.response.used_responses;

import com.ApiCourses.Courses.api.dto.response.custom_responses.CourseBasicResponseToSpecificResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.UserBasicResponse;
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
    private UserBasicResponse sender;
    private UserBasicResponse receiver;
    private CourseBasicResponseToSpecificResponse course;
}
