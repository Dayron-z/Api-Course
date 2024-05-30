package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.SubjectRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.SubjectResponse;

public interface ISubjectService extends  CrudService<SubjectRequest, SubjectResponse, Long>{
}
