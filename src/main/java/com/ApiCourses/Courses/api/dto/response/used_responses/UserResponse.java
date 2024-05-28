package com.ApiCourses.Courses.api.dto.response.used_responses;

import com.ApiCourses.Courses.utils.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private Role role;
    private List<CourseResponse> courses;
    private List<EnrollmentResponse> enrollments;
    private List<MessageResponse> sentMessages;
    private List<MessageResponse> receivedMessages;
}
