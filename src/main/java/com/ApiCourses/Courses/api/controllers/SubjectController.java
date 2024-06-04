package com.ApiCourses.Courses.api.controllers;


import com.ApiCourses.Courses.api.dto.request.custom_request.UpdateSubjectRequest;
import com.ApiCourses.Courses.api.dto.request.used_request.SubjectRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.SubjectResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.ISubjectService;
import com.ApiCourses.Courses.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private final ISubjectService iSubjectService;
    @GetMapping
    public ResponseEntity<Page<SubjectResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestHeader(required = false) SortType sortType){

        if (Objects.isNull(sortType)){
            sortType = SortType.NONE;
        }
        return ResponseEntity.ok(this.iSubjectService.getAll(page - 1, size, sortType));
    }

    @GetMapping(path = "/lessons/{lesson_id}")
    public ResponseEntity<List<SubjectResponse>> get(@PathVariable("lesson_id") Long id){
        return ResponseEntity.ok(this.iSubjectService.findBylessonId(id));
    }

    @PostMapping
    public ResponseEntity<SubjectResponse> create(@Validated @RequestBody SubjectRequest subjectRequest){
        return ResponseEntity.ok(this.iSubjectService.create(subjectRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        this.iSubjectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SubjectResponse> update(@PathVariable Long id,  @Validated @RequestBody UpdateSubjectRequest subjectRequest){
        return ResponseEntity.ok(this.iSubjectService.customUpdate(subjectRequest, id));
    }

}
