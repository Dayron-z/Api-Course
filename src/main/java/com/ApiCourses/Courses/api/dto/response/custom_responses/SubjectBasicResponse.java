package com.ApiCourses.Courses.api.dto.response.custom_responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectBasicResponse {
    private Long  id;
    private String subjectTitle;
    private String text;
    private LocalDateTime dueDate;
}
