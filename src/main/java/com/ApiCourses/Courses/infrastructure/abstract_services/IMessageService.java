package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.MessageRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.MessageResponse;

public interface IMessageService extends CrudService <MessageRequest, MessageResponse, Long> {
}
