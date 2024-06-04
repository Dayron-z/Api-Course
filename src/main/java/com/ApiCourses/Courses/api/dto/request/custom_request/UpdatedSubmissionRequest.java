package com.ApiCourses.Courses.api.dto.request.custom_request;

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
public class UpdatedSubmissionRequest {
    @NotBlank(message = "Content is required.")
    private String content;

    @NotNull(message = "Grade is required.")
    private Double grade;
}
