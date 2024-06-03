package com.ApiCourses.Courses.infrastructure.services;

import com.ApiCourses.Courses.api.dto.request.custom_request.UpdateSubjectRequest;
import com.ApiCourses.Courses.api.dto.request.used_request.SubjectRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.LessonBasicResponseToSubject;
import com.ApiCourses.Courses.api.dto.response.custom_responses.SubmissionBasicResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.UserBasicResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.SubjectResponse;
import com.ApiCourses.Courses.domain.entities.Lesson;
import com.ApiCourses.Courses.domain.entities.Subject;
import com.ApiCourses.Courses.domain.entities.Submission;
import com.ApiCourses.Courses.domain.repositories.LessonRepository;
import com.ApiCourses.Courses.domain.repositories.SubjectRepository;
import com.ApiCourses.Courses.infrastructure.abstract_services.ISubjectService;
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
public class SubjectService implements ISubjectService {
    @Autowired
    private final SubjectRepository subjectRepository;
    @Autowired
    private final LessonRepository lessonRepository;
    @Override
    public SubjectResponse create(SubjectRequest request) {
        Lesson lesson = this.lessonRepository.findById(request.getLessonId()).orElseThrow(()-> new BadRequestException("There are no lessons with the id provided"));


        Subject subject = this.requestToEntity(request);
        subject.setLesson(lesson);
        subject.setSubmissions(new ArrayList<>());

        return this.entityToResponse(this.subjectRepository.save(subject));
    }
    @Override
    public SubjectResponse get(Long id) {
        return null;
    }
    @Override
    public SubjectResponse update(SubjectRequest request, Long aLong) {
        return null;
    }
    public SubjectResponse customUpdate(UpdateSubjectRequest request, Long id) {
        Subject subject = this.findById(id);
        Subject updatedSubject = this.updatedRequestToEntity(request);
        updatedSubject.setId(id);
        updatedSubject.setLesson(subject.getLesson());
        updatedSubject.setSubmissions(subject.getSubmissions());

        return this.entityToResponse(this.subjectRepository.save(updatedSubject));
    }

    @Override
    public List<SubjectResponse> findBylessonId(Long id) {

         List<SubjectResponse>  subjects = this.subjectRepository.findBylessonId(id).stream().map(subject -> this.entityToResponse(subject)).collect(Collectors.toList());
        return subjects ;
    }

    @Override
    public void delete(Long id) {
        Subject subject = this.findById(id);
        this.subjectRepository.delete(subject);
    }
    @Override
    public Page<SubjectResponse> getAll(int page, int size, SortType sort) {
        if (page < 0){
            page = 0;
        }

        PageRequest pagination = null;

        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC));
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC));
        }

        return this.subjectRepository.findAll(pagination).map(subject -> this.entityToResponse(subject)) ;
    }
    private SubjectResponse entityToResponse(Subject subject){
        var lesson = new LessonBasicResponseToSubject();
        BeanUtils.copyProperties(subject.getLesson(), lesson);

        List<SubmissionBasicResponse> submissions = subject.getSubmissions().stream().map(submission -> this.entityToSubmissionResponse(submission)).collect(Collectors.toList());



        return SubjectResponse.builder()
                .id(subject.getId())
                .subjectTitle(subject.getSubjectTitle())
                .text(subject.getText())
                .dueDate(subject.getDueDate())
                .lesson(lesson)
                .submissions(submissions)

                .build();


    }
    private Subject updatedRequestToEntity(UpdateSubjectRequest subjectRequest){

        return Subject.builder()
                .subjectTitle(subjectRequest.getSubjectTitle())
                .text(subjectRequest.getText())
                .dueDate(subjectRequest.getDueDate())
                .build();

    }
    private Subject requestToEntity(SubjectRequest subjectRequest){

        return Subject.builder()
                .subjectTitle(subjectRequest.getSubjectTitle())
                .text(subjectRequest.getText())
                .dueDate(subjectRequest.getDueDate())
                .build();

    }
    private Subject findById(Long id){
        return  this.subjectRepository.findById(id).orElseThrow(()-> new BadRequestException("There are no subjects with the id provided"));
    }
    private SubmissionBasicResponse entityToSubmissionResponse(Submission submission){
        var user = new UserBasicResponse();
        BeanUtils.copyProperties(submission.getUser(), user);



        return SubmissionBasicResponse.builder()
                .id(submission.getId())
                .content(submission.getContent())
                .grade(submission.getGrade())
                .user(user)
                .build();
    }

}
