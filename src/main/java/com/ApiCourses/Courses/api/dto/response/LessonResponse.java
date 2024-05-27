package com.ApiCourses.Courses.api.dto.response;

import com.ApiCourses.Courses.domain.entities.Course;
import com.ApiCourses.Courses.domain.entities.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {
    private Long id;
    private String lessonTitle;
    private String content;
    /*Por revisar*/
    private CourseResponse course;
    /*Por revisar*/
    private List<SubjectResponse> subjects;
}
