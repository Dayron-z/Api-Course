package com.ApiCourses.Courses.api.dto.response.used_responses;

import com.ApiCourses.Courses.api.dto.response.custom_responses.EnrollmentBasicResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.LessonBasicResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.MessageBasicResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.UserBasicResponse;
import com.ApiCourses.Courses.domain.entities.Message;
import com.ApiCourses.Courses.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String courseName;
    private String description;
    private UserBasicResponse instructor;


    /*Se cambia*/
    private List<LessonBasicResponse> lessons;



    private List<EnrollmentBasicResponse> enrollments;
    private List<MessageBasicResponse> messages;
}






