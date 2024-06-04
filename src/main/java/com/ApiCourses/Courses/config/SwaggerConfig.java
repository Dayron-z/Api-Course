package com.ApiCourses.Courses.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Course",
                description = "Courses",
                version = "1.0.0",
                contact = @Contact(
                        name = "School",
                        url = "School.com",
                        email = "school@gmail.com"
                )
        )
)
public class SwaggerConfig {

}
