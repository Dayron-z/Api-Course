package com.ApiCourses.Courses.api.dto.response.custom_responses;

import com.ApiCourses.Courses.api.dto.response.used_responses.SubjectResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonBasicResponse {
    private Long id;
    private String lessonTitle;
    private String content;
    private List<SubjectBasicResponse> subjects;
}
