package com.ApiCourses.Courses.api.dto.response.used_responses;


import com.ApiCourses.Courses.api.dto.response.custom_responses.LessonBasicResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.LessonBasicResponseToSubject;
import com.ApiCourses.Courses.api.dto.response.custom_responses.SubmissionBasicResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponse {
    private Long  id;
    private String subjectTitle;
    private String text;
    private LocalDateTime dueDate;
    private LessonBasicResponseToSubject lesson;
    private List<SubmissionBasicResponse> submissions;
}
