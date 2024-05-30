package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.EnrollmentRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.EnrollmentResponse;

public interface IEnrollmentService extends  CrudService<EnrollmentRequest, EnrollmentResponse, Long>{

}
