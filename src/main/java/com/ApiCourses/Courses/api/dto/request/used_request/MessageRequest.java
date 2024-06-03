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
public class MessageRequest {

    @NotBlank(message = "The message content cannot be blank.")
    @Size(max = 255, message = "The message content must not exceed {max} characters.")
    private String message;

    @NotNull(message = "The sender ID is required.")
    private Long senderId;

    @NotNull(message = "The receiver ID is required.")
    private Long receiverId;

    @NotNull(message = "The course ID is required.")
    private Long courseId;
}
