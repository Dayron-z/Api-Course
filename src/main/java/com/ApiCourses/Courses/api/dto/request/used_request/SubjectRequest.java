package com.ApiCourses.Courses.api.dto.request.used_request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
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
public class SubjectRequest {
    @NotBlank(message = "The subject title cannot be blank.")
    private String subjectTitle;

    @NotBlank(message = "The text cannot be blank.")
    private String text;

    @FutureOrPresent(message = "The due date must be in the present or future.")
    @NotNull(message = "The due date is required.")
    private LocalDateTime dueDate;

    @NotNull(message = "The lesson ID is required.")
    private Long lessonId;
}
