package com.ApiCourses.Courses.infrastructure.services;

import com.ApiCourses.Courses.api.dto.request.UserRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.*;
import com.ApiCourses.Courses.api.dto.response.used_responses.UserResponse;
import com.ApiCourses.Courses.domain.entities.*;
import com.ApiCourses.Courses.domain.repositories.UserRepository;
import com.ApiCourses.Courses.infrastructure.abstract_services.IUserService;
import com.ApiCourses.Courses.utils.enums.SortType;
import com.ApiCourses.Courses.utils.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private  final UserRepository userRepository;
    @Override
    public UserResponse create(UserRequest request) {
        UserEntity user = this.requestToEntity(request);
        user.setCourses(new ArrayList<>());
        user.setSubmissions(new ArrayList<>());
        user.setEnrollments(new ArrayList<>());
        user.setSentMessages(new ArrayList<>());
        user.setReceivedMessages(new ArrayList<>());

        return this.entityToResponse(this.userRepository.save(user));
    }
    @Override
    public UserResponse get(Long id) {
        UserEntity user = find(id);
        return entityToResponse(user);
    }
    @Override
    public UserResponse update(UserRequest request, Long id) {
        UserEntity user = this.find(id);
        UserEntity userUpdated = this.requestToEntity(request);
        userUpdated.setId(id);
        userUpdated.setCourses(user.getCourses());
        userUpdated.setSubmissions(user.getSubmissions());
        userUpdated.setEnrollments(user.getEnrollments());
        userUpdated.setSentMessages(user.getSentMessages());
        userUpdated.setReceivedMessages(user.getReceivedMessages());


        return this.entityToResponse(this.userRepository.save(userUpdated));
    }
    @Override
    public void delete(Long id) {
        UserEntity user = find(id);
        this.userRepository.delete(user);
    }
    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sort) {

/*        if (page < 0){
            page = 0;
        }

        PageRequest pagination = null;

        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            // FIELD_BY_SORT is just an attribute in the interface that contains a string with the name of an entity attribute.
            // We use this attribute name to sort the results according to that rule.
            case ASC ->  pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }*/

        return null;
    }
    private UserEntity find(Long id){
        return this.userRepository.findById(id).orElseThrow(()-> new BadRequestException("There are no users with the id provided"));
    }
    private UserResponse entityToResponse(UserEntity user) {

        List<SubmissionBasicResponseToUser> submissions =  user.getSubmissions().stream().map(submission -> this.entityToSubmission(submission)).collect(Collectors.toList());

        List<CourseBasicResponseToUserResponse> courses =  user.getCourses().stream().map(course -> this.entityToCourse(course)).collect(Collectors.toList());

        List<MessageBasicResponseToUserResponse> sentMessages = user.getSentMessages().stream().map(message -> this.entityToMessage(message)).collect(Collectors.toList());

        List<MessageBasicResponseToUserResponse> receivedMessages = user.getReceivedMessages().stream().map(message -> this.entityToMessage(message)).collect(Collectors.toList());


        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .submissions(submissions)
                .courses(courses)
                .sentMessages(sentMessages)
                .receivedMessages(receivedMessages)
                .build();

    }
    private SubmissionBasicResponseToUser entityToSubmission(Submission submission){

        SubjectBasicResponse subject = new SubjectBasicResponse();
        BeanUtils.copyProperties(submission.getSubject() , subject);


        return SubmissionBasicResponseToUser.builder()
                .id(submission.getId())
                .content(submission.getContent())
                .grade(submission.getGrade())
                .subject(subject)
                .build();
    }
    private CourseBasicResponseToUserResponse entityToCourse(Course course){
        UserBasicResponse instructor = new UserBasicResponse();
        BeanUtils.copyProperties(course.getUser(), instructor);

        List<LessonBasicResponse> lessons =  course.getLessons().stream().map(lesson -> this.entityToLesson(lesson)).collect(Collectors.toList());

       return CourseBasicResponseToUserResponse.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .instructor(instructor)
                .lessons(lessons)

                .build();

    }
    private LessonBasicResponse entityToLesson(Lesson lesson){
        List<SubjectBasicResponse> subjects =  lesson.getSubjects().stream().map(subject -> this.entityToSubject(subject)).collect(Collectors.toList());


        return LessonBasicResponse.builder()
                .id(lesson.getId())
                .lessonTitle(lesson.getLessonTitle())
                .content(lesson.getContent())
                .subjects(subjects)
                .build();
    }
    private SubjectBasicResponse entityToSubject(Subject subject){

        return SubjectBasicResponse.builder()
                .id(subject.getId())
                .subjectTitle(subject.getSubjectTitle())
                .text(subject.getText())
                .dueDate(subject.getDueDate())
                .build();
    }
    private MessageBasicResponseToUserResponse entityToMessage(Message message){
        return MessageBasicResponseToUserResponse.builder()
                .message(message.getMessage())
                .build();
    }
    private UserEntity requestToEntity(UserRequest userRequest){
        return UserEntity.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .fullName(userRequest.getFullName())
                .role(userRequest.getRole())
                .build();


    }

}
