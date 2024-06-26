package com.ApiCourses.Courses.api.dto.response.custom_responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentBasicResponseToUser {
    private Long id;
    private LocalDate enrollmentDate;
    private CourseBasicResponseToSpecificResponse course;
}
