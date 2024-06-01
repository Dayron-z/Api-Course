package com.ApiCourses.Courses.api.dto.response.used_responses;

import com.ApiCourses.Courses.api.dto.response.custom_responses.*;
import com.ApiCourses.Courses.domain.entities.Submission;
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
    /*Listo*/
    private List<SubmissionBasicResponseToUser> submissions;
    /*Listo*/
    private List<CourseBasicResponse> courses;
    private List<EnrollmentBasicResponseToUser> enrollments;
    private List<MessageBasicResponse> sentMessages;
    private List<MessageBasicResponse> receivedMessages;
}
