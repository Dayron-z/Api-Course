package com.ApiCourses.Courses.api.dto.response;


import com.ApiCourses.Courses.domain.entities.Submission;
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
    private LessonResponse lesson;
    /*Por verificar*/
    private List<Submission> submissions;
}
