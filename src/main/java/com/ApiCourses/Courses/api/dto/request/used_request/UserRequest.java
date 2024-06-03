package com.ApiCourses.Courses.api.dto.request.used_request;

import com.ApiCourses.Courses.utils.enums.Role;
import jakarta.validation.constraints.Email;
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
public class UserRequest {

    @NotBlank(message = "Username is required.")
    @Size(max = 50, message = "Username must not exceed {max} characters.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(max = 50, message = "Password must not exceed {max} characters.")
    private String password;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    @Size(max = 70, message = "Email must not exceed {max} characters.")
    private String email;

    @NotBlank(message = "Full name is required.")
    @Size(max = 100, message = "Full name must not exceed {max} characters.")
    private String fullName;

    @NotNull(message = "Role is required.")
    private Role role;

}
