package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.UserRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.UserResponse;
import com.ApiCourses.Courses.domain.entities.UserEntity;
import lombok.AllArgsConstructor;

public interface IUserService extends CrudService<UserRequest, UserResponse, Long>{
    public final String FIELD_BY_SORT = "fullName";
}



