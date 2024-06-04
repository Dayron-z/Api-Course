package com.ApiCourses.Courses.api.dto.response.custom_responses;

import com.ApiCourses.Courses.domain.entities.UserEntity;
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
public class EnrollmentBasicResponse {
    private Long id;
    private LocalDate enrollmentDate;
    private UserBasicResponse user;
}
