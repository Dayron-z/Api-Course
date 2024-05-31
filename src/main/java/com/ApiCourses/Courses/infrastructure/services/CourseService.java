package com.ApiCourses.Courses.infrastructure.services;


import com.ApiCourses.Courses.api.dto.request.CourseRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.*;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.domain.entities.*;
import com.ApiCourses.Courses.domain.repositories.CourseRepository;
import com.ApiCourses.Courses.domain.repositories.UserRepository;
import com.ApiCourses.Courses.infrastructure.abstract_services.ICourseService;
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
public class CourseService implements ICourseService {

    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final UserRepository userRepository;
    @Override
    public CourseResponse create(CourseRequest request) {
        Course course = this.requestToEntity(request);
        course.setLessons(new ArrayList<>());
        course.setEnrollments(new ArrayList<>());
        course.setMessages(new ArrayList<>());
        course.setUser(this.userRepository.findById(request.getId()).orElseThrow(()->
                new BadRequestException("There are no users with the id provided")));
        return this.entityToResponse(this.courseRepository.save(course));
    }
    @Override
    public CourseResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }
    @Override
    public CourseResponse update(CourseRequest request, Long id) {
        Course course = this.find(id);

        Course updatedCourse = this.requestToEntity(request);
        updatedCourse.setId(id);
        updatedCourse.setUser(course.getUser());


        return this.entityToResponse(this.courseRepository.save(updatedCourse));

    }
    @Override
    public void delete(Long id) {
        Course course = this.find(id);
        this.courseRepository.delete(course);
    }
    @Override
    public Page<CourseResponse> getAll(int page, int size, SortType sort) {

        if (page < 0){
            page = 0;
        }

        PageRequest pagination = null;

        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC));
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC));
        }

        return this.courseRepository.findAll(pagination).map(course -> this.entityToResponse(course));
    }
    private Course find(Long id){
        return this.courseRepository.findById(id).orElseThrow(()-> new BadRequestException("There are no courses with the id provided"));
    }
    private Course requestToEntity(CourseRequest courseRequest){
        return Course.builder()
                .courseName(courseRequest.getCourseName())
                .description(courseRequest.getDescription())
                .build();
    }
    private CourseResponse entityToResponse(Course course){
        //Convertimos los atributos que están customizados
        UserBasicResponse instructor = new UserBasicResponse();
        BeanUtils.copyProperties(course.getUser(), instructor);

        //Para la listas lo hacemos de forma  similar
        List<LessonBasicResponse> lessons = course.getLessons().stream().map(lesson -> this.entityToLessonResponse(lesson)).collect(Collectors.toList());

        List<EnrollmentBasicResponse> enrollments = course.getEnrollments().stream().map(enrollment -> this.entityToEnrollmentResponse(enrollment)).collect(Collectors.toList());

        List<MessageBasicResponse> messages = course.getMessages().stream().map(message -> this.entityToMessageResponse(message)).collect(Collectors.toList());



        return CourseResponse.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .instructor(instructor)
                .lessons(lessons)
                .enrollments(enrollments)
                .messages(messages)
                .build();
    }
    private MessageBasicResponse entityToMessageResponse(Message message){
        //Convertimos los atributos que están customizados
        UserBasicResponse sender = new UserBasicResponse();
        BeanUtils.copyProperties(message.getSender(), sender);


        //Convertimos los atributos que están customizados
        UserBasicResponse receiver = new UserBasicResponse();
        BeanUtils.copyProperties(message.getReceiver(), receiver);


        return  MessageBasicResponse.builder()
                .message(message.getMessage())
                .sender(sender)
                .receiver(receiver)
                .build();

    }
    private EnrollmentBasicResponse entityToEnrollmentResponse(Enrollment enrollment) {
        UserBasicResponse student = new UserBasicResponse();
        BeanUtils.copyProperties(enrollment.getUser(), student);


        return EnrollmentBasicResponse.builder()
                .id(enrollment.getId())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .user(student)
                .build();
    }
    private LessonBasicResponse entityToLessonResponse(Lesson lesson){
        List<SubjectBasicResponse> subjects = lesson.getSubjects().stream().map(subject ->
                this.entityToSubjectResponse(subject)).collect(Collectors.toList());


        return LessonBasicResponse.builder()
                .id(lesson.getId())
                .lessonTitle(lesson.getLessonTitle())
                .content(lesson.getContent())
                .subjects(subjects)
                .build();

    }
    private SubjectBasicResponse entityToSubjectResponse(Subject subject) {
        return SubjectBasicResponse.builder()
                .id(subject.getId())
                .subjectTitle(subject.getSubjectTitle())
                .text(subject.getText())
                .dueDate(subject.getDueDate())
                .build();
    }

}
