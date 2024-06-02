package com.ApiCourses.Courses.api.dto.request;

import com.ApiCourses.Courses.domain.entities.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequest {
    @NotNull(message = "The user ID is required.")
    private Long userId;

    @NotNull(message = "The course ID is required.")
    private Long courseId;
}
