package com.ApiCourses.Courses.infrastructure.services;


import com.ApiCourses.Courses.api.dto.request.custom_request.UpdatedSubmissionRequest;
import com.ApiCourses.Courses.api.dto.request.used_request.SubmissionRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.SubjectBasicResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.UserBasicResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.SubmissionResponse;
import com.ApiCourses.Courses.domain.entities.Subject;
import com.ApiCourses.Courses.domain.entities.Submission;
import com.ApiCourses.Courses.domain.entities.UserEntity;
import com.ApiCourses.Courses.domain.repositories.SubjectRepository;
import com.ApiCourses.Courses.domain.repositories.SubmissionRepository;
import com.ApiCourses.Courses.domain.repositories.UserRepository;
import com.ApiCourses.Courses.infrastructure.abstract_services.ISubmissionService;
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
public class SubmissionService implements ISubmissionService {

    @Autowired
    private final SubmissionRepository submissionRepository;

    @Autowired
    private final SubjectRepository subjectRepository;

    @Autowired
    private final UserRepository userRepository;


    @Override
    public SubmissionResponse create(SubmissionRequest request) {
        Submission submission = this.requestToEntity(request);
        Subject subject = this.subjectRepository.findById(request.getSubjectId()).orElseThrow(()-> new BadRequestException("There are no subjects with the id provided"));

        UserEntity user = this.userRepository.findById(request.getUserId()).orElseThrow(()-> new BadRequestException("There are no users with the id provided"));

        submission.setSubject(subject);
        submission.setUser(user);
        submission.setSubmission_date(LocalDateTime.now());

        return this.entityToResponse(this.submissionRepository.save(submission));
    }

    @Override
    public SubmissionResponse get(Long id) {
        Submission submission  = this.findById(id);
        return this.entityToResponse(submission) ;
    }

    @Override
    public SubmissionResponse update(SubmissionRequest request, Long aLong) {
        return null;
    }

    public SubmissionResponse customUpdate(UpdatedSubmissionRequest request, Long id) {
        Submission submission = this.findById(id);
        Submission updatedSubmission = this.updatedRequestToEntity(request);

        updatedSubmission.setId(id);
        updatedSubmission.setUser(submission.getUser());
        updatedSubmission.setSubject(submission.getSubject());

        return this.entityToResponse(this.submissionRepository.save(updatedSubmission)) ;
    }

    @Override
    public List<SubmissionResponse> findBySubjectId(Long id) {
        return this.submissionRepository.findBySubjectId(id).stream().map(submission -> this.entityToResponse(submission)).collect(Collectors.toList());
    }
    @Override
    public List<SubmissionResponse> findByUserId(Long id) {
         return this.submissionRepository.findByUserId(id).stream().map(submission -> this.entityToResponse(submission)).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) {
        Submission submission  = this.findById(id);
        this.submissionRepository.delete(submission);
    }
    @Override
    public Page<SubmissionResponse> getAll(int page, int size, SortType sort) {
        return null;
    }
    private SubmissionResponse entityToResponse(Submission submission){
        var subject = new SubjectBasicResponse();
        BeanUtils.copyProperties( submission.getSubject() , subject);

        var user = new UserBasicResponse();
        BeanUtils.copyProperties( submission.getUser() , user);

        return SubmissionResponse.builder()
                .id(submission.getId())
                .content(submission.getContent())
                .grade(submission.getGrade())
                .subject(subject)
                .user(user)
                .build();
    }
    private Submission updatedRequestToEntity(UpdatedSubmissionRequest submission){
        return Submission.builder()
                .content(submission.getContent())
                .grade(submission.getGrade())
                .build();
    }
    private Submission requestToEntity(SubmissionRequest submission){


        return Submission.builder()
                .content(submission.getContent())
                .grade(submission.getGrade())
                .build();
    }
    private Submission findById(Long id){
        return this.submissionRepository.findById(id).orElseThrow(() -> new BadRequestException("There are no submissions with the id provided"));
    }

}
