package com.ApiCourses.Courses.api.dto.response.used_responses;

import com.ApiCourses.Courses.api.dto.response.custom_responses.CourseBasicResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.SubjectBasicResponse;
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
    private CourseBasicResponse course;
    private List<SubjectBasicResponse> subjects;
}


/*Hecha*/
