package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.LessonRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.LessonResponse;

public interface ILessonService extends CrudService<LessonRequest, LessonResponse, Long> {
}
