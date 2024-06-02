package com.ApiCourses.Courses.api.controllers;



import com.ApiCourses.Courses.api.dto.request.CourseRequest;
import com.ApiCourses.Courses.api.dto.request.UserRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.UserResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.ICourseService;
import com.ApiCourses.Courses.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/course")
@AllArgsConstructor
public class CourseController {
    private final ICourseService courseService;


    @GetMapping
    public ResponseEntity<Page<CourseResponse>>getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestHeader(required = false) SortType sortType){

        if (Objects.isNull(sortType)){
            sortType = SortType.NONE;
        }
        return ResponseEntity.ok(this.courseService.getAll(page - 1, size, sortType));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseResponse>get(@PathVariable Long id){
        return ResponseEntity.ok(this.courseService.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.courseService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<CourseResponse> update(@PathVariable Long id,  @RequestBody CourseRequest courseRequest){
        return ResponseEntity.ok(this.courseService.update(courseRequest, id));
    }

    @PostMapping
    public ResponseEntity<CourseResponse> create(@Validated @RequestBody CourseRequest courseRequest){
        return ResponseEntity.ok(this.courseService.create(courseRequest));
    }

}
