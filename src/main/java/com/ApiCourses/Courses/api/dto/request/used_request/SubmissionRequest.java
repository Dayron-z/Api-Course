package com.ApiCourses.Courses.api.dto.request.used_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionRequest {
    @NotBlank(message = "Content is required.")
    private String content;

    @NotNull(message = "Grade is required.")
    private Double grade;

    @NotNull(message = "Subject ID is required.")
    private Long subjectId;

    @NotNull(message = "User ID is required.")
    private Long userId;
}
