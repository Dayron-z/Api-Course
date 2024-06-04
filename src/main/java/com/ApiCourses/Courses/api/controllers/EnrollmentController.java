package com.ApiCourses.Courses.api.controllers;


import com.ApiCourses.Courses.api.dto.request.used_request.CourseRequest;
import com.ApiCourses.Courses.api.dto.request.used_request.EnrollmentRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.EnrollmentBasicResponseToUser;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.EnrollmentResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.ICourseService;
import com.ApiCourses.Courses.infrastructure.abstract_services.IEnrollmentService;
import com.ApiCourses.Courses.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/enrollment")
@AllArgsConstructor
public class EnrollmentController {
    private final IEnrollmentService enrollmentService;
    @GetMapping(path = "/{id}")
    public ResponseEntity<EnrollmentResponse>get(@PathVariable Long id){
        return ResponseEntity.ok(this.enrollmentService.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponse> create(@Validated @RequestBody EnrollmentRequest enrollmentRequest){
        return ResponseEntity.ok(this.enrollmentService.create(enrollmentRequest));
    }

    @GetMapping(path = "/users/{user_id}/courses")
    public ResponseEntity<List<EnrollmentBasicResponseToUser>> findCoursesByUser(@PathVariable("user_id") Long id){
        return ResponseEntity.ok(this.enrollmentService.findByUserId(id));
    }


}
