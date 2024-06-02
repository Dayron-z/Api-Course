package com.ApiCourses.Courses.api.dto.response.used_responses;

import com.ApiCourses.Courses.api.dto.response.custom_responses.*;
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
    private List<SubmissionBasicResponseToUser> submissions;
    private List<CourseBasicResponseToUserResponse> courses;
    private List<MessageBasicResponseToUserResponse> sentMessages;
    private List<MessageBasicResponseToUserResponse> receivedMessages;
}
