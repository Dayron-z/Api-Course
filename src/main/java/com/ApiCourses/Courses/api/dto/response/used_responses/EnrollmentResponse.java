package com.ApiCourses.Courses.api.dto.response.used_responses;


import com.ApiCourses.Courses.api.dto.response.custom_responses.CourseBasicResponse;
import com.ApiCourses.Courses.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponse {
    private Long id;
    private LocalDateTime enrollmentDate;
    /*Por revisar*/
    private UserEntity user;
    private CourseBasicResponse course;

}
