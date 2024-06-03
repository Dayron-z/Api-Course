package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.used_request.CourseRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;

public interface ICourseService extends CrudService <CourseRequest, CourseResponse, Long> {
}
