package com.ApiCourses.Courses.api.controllers;


import com.ApiCourses.Courses.api.dto.request.custom_request.UpdatedSubmissionRequest;
import com.ApiCourses.Courses.api.dto.request.used_request.SubmissionRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.SubmissionResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.ISubmissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/submission")
public class SubmissionController {

    private final ISubmissionService submissionService;
    @GetMapping(path = "/{id}")
    public ResponseEntity<SubmissionResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.submissionService.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.submissionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<SubmissionResponse> create(@Validated @RequestBody SubmissionRequest submissionRequest){
        return ResponseEntity.ok(this.submissionService.create(submissionRequest));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SubmissionResponse> update(@PathVariable Long id, @Validated @RequestBody UpdatedSubmissionRequest submissionRequest){
        return ResponseEntity.ok(this.submissionService.customUpdate(submissionRequest, id));
    }

    @GetMapping(path = "/subject/{subject_id}")
    public ResponseEntity<List<SubmissionResponse>> getBySubject(@PathVariable("subject_id") Long id){
        return ResponseEntity.ok(this.submissionService.findBySubjectId(id));
    }


    @GetMapping(path = "/users/{user_id}")
    public ResponseEntity<List<SubmissionResponse>> getByUser(@PathVariable("user_id") Long id){
        return ResponseEntity.ok(this.submissionService.findByUserId(id));
    }


}
