package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.used_request.UserRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.UserResponse;

public interface IUserService extends CrudService<UserRequest, UserResponse, Long>{
    public final String FIELD_BY_SORT = "fullName";
}



