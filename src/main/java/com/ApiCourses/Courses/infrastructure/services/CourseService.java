package com.ApiCourses.Courses.infrastructure.services;


import com.ApiCourses.Courses.api.dto.request.CourseRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.*;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.domain.entities.*;
import com.ApiCourses.Courses.infrastructure.abstract_services.ICourseService;
import com.ApiCourses.Courses.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService implements ICourseService {

    @Override
    public CourseResponse create(CourseRequest request) {
        return null;
    }

    @Override
    public CourseResponse get(Long id) {
        return null;
    }

    @Override
    public CourseResponse update(CourseRequest request, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<CourseResponse> getAll(int page, int size, SortType sort) {
        return null;
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
