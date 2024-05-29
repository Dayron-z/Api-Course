package com.ApiCourses.Courses.api.dto.response.custom_responses;

import com.ApiCourses.Courses.utils.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private Role role;
}
