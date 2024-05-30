package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.SubmissionRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.SubmissionResponse;

public interface ISubmissionService extends  CrudService<SubmissionRequest, SubmissionResponse, Long>{
}
