package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.used_request.LessonRequest;
import com.ApiCourses.Courses.api.dto.request.custom_request.UpdateLessonRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.CourseResponseToLessons;
import com.ApiCourses.Courses.api.dto.response.used_responses.LessonResponse;

public interface ILessonService extends CrudService<LessonRequest, LessonResponse, Long> {
    public LessonResponse customUpdate(UpdateLessonRequest request, Long id);
    public CourseResponseToLessons findByCourse(Long id);
    public final String FIELD_BY_SORT = "lessonTitle";
}
