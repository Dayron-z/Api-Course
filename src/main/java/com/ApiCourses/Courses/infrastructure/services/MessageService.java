package com.ApiCourses.Courses.infrastructure.services;

import com.ApiCourses.Courses.api.dto.request.used_request.MessageRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.CourseBasicResponseToSpecificResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.MessageBasicResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.UserBasicResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.MessageResponse;
import com.ApiCourses.Courses.domain.entities.Course;
import com.ApiCourses.Courses.domain.entities.Message;
import com.ApiCourses.Courses.domain.entities.UserEntity;
import com.ApiCourses.Courses.domain.repositories.CourseRepository;
import com.ApiCourses.Courses.domain.repositories.MessageRespository;
import com.ApiCourses.Courses.domain.repositories.UserRepository;
import com.ApiCourses.Courses.infrastructure.abstract_services.IMessageService;
import com.ApiCourses.Courses.utils.enums.SortType;
import com.ApiCourses.Courses.utils.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService {

    @Autowired
    private final MessageRespository messageRespository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final CourseRepository courseRepository;

    @Override
    public MessageResponse create(MessageRequest request) {
        Message message = this.requestToEntity(request);
        UserEntity sender = this.userRepository.findById(request.getSenderId()).orElseThrow(() -> new BadRequestException("There are no users with the id provided"));

        UserEntity receiver = this.userRepository.findById(request.getReceiverId()).orElseThrow(() -> new BadRequestException("There are no users with the id provided"));

        Course course = this.courseRepository.findById(request.getCourseId()).orElseThrow(() -> new BadRequestException("There are no courses with the id provided"));

        message.setDate(LocalDateTime.now());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setCourse(course);

        return this.entityToResponse(this.messageRespository.save(message));
    }


    @Override
    public List<MessageResponse> findByCourseId(Long id) {
        return this.messageRespository.findByCourseId(id).stream().map(message -> this.entityToResponse(message)).collect(Collectors.toList());
    }

    @Override
    public List<MessageResponse> findBySenderIdAndReceiverId(Long senderId, Long receiverId) {
        return this.messageRespository.findBySenderIdAndReceiverId(senderId, receiverId).stream().map(message -> this.entityToResponse(message)).collect(Collectors.toList());
    }

    @Override
    public MessageResponse get(Long id) {
        return null;
    }

    @Override
    public MessageResponse update(MessageRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {
    }
    @Override
    public Page<MessageResponse> getAll(int page, int size, SortType sort) {
        return null;
    }
    private MessageResponse entityToResponse(Message message){
        var sender = new UserBasicResponse();
        var receiver = new UserBasicResponse();
        var course = new CourseBasicResponseToSpecificResponse();
        var instructor = new UserBasicResponse();

        BeanUtils.copyProperties(message.getSender(), sender);
        BeanUtils.copyProperties(message.getReceiver(), receiver);
        BeanUtils.copyProperties(message.getCourse(), course);
        BeanUtils.copyProperties(message.getCourse().getUser(), instructor);
        course.setInstructor(instructor);

        return MessageResponse.builder()
                .message(message.getMessage())
                .sender(sender)
                .receiver(receiver)
                .course(course)
                .build();
    }

    private Message requestToEntity(MessageRequest request){
        return Message.builder()
                .message(request.getMessage())
                .build();

    }

    private Message findById(Long id){
        return this.messageRespository.findById(id).orElseThrow(()-> new BadRequestException("There are no messages with the id provided"));
    }
}
