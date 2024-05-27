package com.ApiCourses.Courses.api.dto.response;

import com.ApiCourses.Courses.domain.entities.Enrollment;
import com.ApiCourses.Courses.domain.entities.Lesson;
import com.ApiCourses.Courses.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String courseName;
    private String description;
    /*Por revisar*/
    private UserEntity user;
    private List<LessonResponse> lessons;
    private List<EnrollmentResponse> enrollments;
}
