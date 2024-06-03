package com.ApiCourses.Courses.api.dto.request.used_request;

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
public class LessonRequest {
    @NotBlank(message = "The lesson title cannot be blank.")
    @Size(max = 100, message = "The lesson title must not exceed 100 characters.")
    private String lessonTitle;

    @NotBlank(message = "The content cannot be blank.")
    private String content;

    @NotNull(message = "The course ID is required.")
    private Long courseId;
}
