package com.ApiCourses.Courses.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    @NotNull(message = "The id is required.")
    private Long id;

    @NotBlank(message = "The course name cannot be blank.")
    @Size(max = 100, message = "The course name must not exceed 100 characters.")
    private String courseName;

    @NotBlank(message = "The description cannot be blank.")
    private String description;

    @NotNull(message = "The instructor ID is required.")
    private Long instructorId;
}
