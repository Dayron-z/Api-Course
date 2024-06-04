package com.ApiCourses.Courses.infrastructure.abstract_services;

import com.ApiCourses.Courses.api.dto.request.used_request.MessageRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.MessageBasicResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.MessageResponse;
import com.ApiCourses.Courses.domain.entities.Message;

import java.util.List;

public interface IMessageService extends CrudService <MessageRequest, MessageResponse, Long> {
    List<MessageResponse> findByCourseId(Long id);
    List<MessageResponse> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
