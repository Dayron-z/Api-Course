package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.custom_request.UpdatedSubmissionRequest;
import com.ApiCourses.Courses.api.dto.request.used_request.SubmissionRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.SubmissionResponse;

import java.util.List;

public interface ISubmissionService extends  CrudService<SubmissionRequest, SubmissionResponse, Long>{
    public SubmissionResponse customUpdate(UpdatedSubmissionRequest request, Long id);
    public List<SubmissionResponse> findBySubjectId(Long id);
    public List<SubmissionResponse> findByUserId(Long id);
}
