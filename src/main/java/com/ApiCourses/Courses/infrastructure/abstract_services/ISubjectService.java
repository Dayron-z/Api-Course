package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.custom_request.UpdateSubjectRequest;
import com.ApiCourses.Courses.api.dto.request.used_request.SubjectRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.SubjectResponse;
import com.ApiCourses.Courses.domain.entities.Subject;

import java.util.List;

public interface ISubjectService extends  CrudService<SubjectRequest, SubjectResponse, Long>{
    public SubjectResponse customUpdate(UpdateSubjectRequest request, Long id);
    public List<SubjectResponse> findBylessonId(Long id);
}
