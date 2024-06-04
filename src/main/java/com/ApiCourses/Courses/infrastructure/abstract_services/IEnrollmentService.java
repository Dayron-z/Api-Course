package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.used_request.EnrollmentRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.CourseBasicResponseToSpecificResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.EnrollmentBasicResponseToUser;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.EnrollmentResponse;
import com.ApiCourses.Courses.domain.entities.Course;
import com.ApiCourses.Courses.domain.entities.Enrollment;

import java.util.List;

public interface IEnrollmentService extends  CrudService<EnrollmentRequest, EnrollmentResponse, Long>{
    public List<EnrollmentBasicResponseToUser> findByUserId(Long id);
}
