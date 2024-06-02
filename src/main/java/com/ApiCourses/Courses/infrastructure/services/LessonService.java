package com.ApiCourses.Courses.infrastructure.services;

import com.ApiCourses.Courses.api.dto.request.LessonRequest;
import com.ApiCourses.Courses.api.dto.request.UpdateLessonRequest;
import com.ApiCourses.Courses.api.dto.request.UserRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.*;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.LessonResponse;
import com.ApiCourses.Courses.domain.entities.*;
import com.ApiCourses.Courses.domain.repositories.CourseRepository;
import com.ApiCourses.Courses.domain.repositories.LessonRepository;
import com.ApiCourses.Courses.infrastructure.abstract_services.ICourseService;
import com.ApiCourses.Courses.infrastructure.abstract_services.ILessonService;
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
public class LessonService implements ILessonService {
    @Autowired
    private final LessonRepository lessonRepository;
    @Autowired
    private final CourseRepository courseRepository;
    @Override
    public LessonResponse create(LessonRequest request) {

        Course course = this.courseRepository.findById(request.getCourseId()).orElseThrow(() -> new BadRequestException("There are no courses with the id provided"));

        Lesson lesson = this.requestToEntity(request);
        lesson.setCourse(course);
        lesson.setSubjects(new ArrayList<>());


        return this.entityToResponse(this.lessonRepository.save(lesson));
    }
    @Override
    public LessonResponse get(Long id) {
        return  this.entityToResponse(this.find(id));
    }
    @Override
    public LessonResponse update(LessonRequest request, Long id) {
        return null;
    }
    public LessonResponse customUpdate(UpdateLessonRequest request, Long id) {
        Lesson lesson = this.find(id);
        Lesson updatedLesson  = this.requestToUpdateEntity(request);
        updatedLesson.setId(id);
        updatedLesson.setCourse(lesson.getCourse());
        updatedLesson.setSubjects(lesson.getSubjects());

        return this.entityToResponse( this.lessonRepository.save(updatedLesson));
    }

    @Override
    public void delete(Long id) {
        Lesson lesson = this.find(id);
        this.lessonRepository.delete(lesson);
    }
    @Override
    public Page<LessonResponse> getAll(int page, int size, SortType sort) {
        return null;
    }


    public CourseResponseToLessons findByCourse(Long id){
        Course course =  this.courseRepository.findById(id).orElseThrow(()-> new BadRequestException("There are no courses with the id provided"));
        return  this.courseToCourseResponse(course);

    }

    private CourseResponseToLessons courseToCourseResponse(Course course){
        List<LessonBasicResponse> lessons = course.getLessons().stream().map(lesson -> this.entityToCustomLessonResponse(lesson)).collect(Collectors.toList());

        return CourseResponseToLessons.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .lessons(lessons)
                .build();
    }

    private LessonBasicResponse entityToCustomLessonResponse(Lesson lesson){

        List<SubjectBasicResponse> subjects =  lesson.getSubjects().stream().map( subject -> this.entityToLessonResponse(subject)).collect(Collectors.toList());


        return LessonBasicResponse.builder()
                .id(lesson.getId())
                .lessonTitle(lesson.getLessonTitle())
                .content(lesson.getContent())
                .subjects(subjects)
                .build();

    }
    private SubjectBasicResponse entityToLessonResponse(Subject subject){

       return SubjectBasicResponse.builder()
                .id(subject.getId())
                .subjectTitle(subject.getSubjectTitle())
                .text(subject.getText())
                .dueDate(subject.getDueDate())
                .build();
    }





    private Lesson requestToEntity(LessonRequest lessonRequest){
        return Lesson.builder()
                .lessonTitle(lessonRequest.getLessonTitle())
                .content(lessonRequest.getContent())
                .build();
    }
    private Lesson requestToUpdateEntity(UpdateLessonRequest lessonRequest){
        return Lesson.builder()
                .lessonTitle(lessonRequest.getLessonTitle())
                .content(lessonRequest.getContent())
                .build();
    }
    private LessonResponse entityToResponse(Lesson lesson){

        CourseBasicResponseToSpecificResponse course = this.entityToCourseResponse(lesson.getCourse());

        List<SubjectBasicResponse> subjects = lesson.getSubjects().stream().map(subject -> this.entityToSubjectResponse(subject)).collect(Collectors.toList());


        return LessonResponse.builder()
                .id(lesson.getId())
                .lessonTitle(lesson.getLessonTitle())
                .content(lesson.getContent())
                .course(course)
                .subjects(subjects)
                .build();
    }
    private CourseBasicResponseToSpecificResponse entityToCourseResponse(Course course){
        UserBasicResponse instructor = new UserBasicResponse();
        BeanUtils.copyProperties(course.getUser(), instructor);


        return CourseBasicResponseToSpecificResponse.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .instructor(instructor)
                .build();
    }
    private SubjectBasicResponse entityToSubjectResponse(Subject subject){
       return SubjectBasicResponse.builder()
                .id(subject.getId())
                .subjectTitle(subject.getSubjectTitle())
                .text(subject.getText())
                .dueDate(subject.getDueDate())
                .build();
    }
    private Lesson find(Long id){
        return  this.lessonRepository.findById(id).orElseThrow(() -> new BadRequestException("There are no lessons with the id provided"));
    }
}
