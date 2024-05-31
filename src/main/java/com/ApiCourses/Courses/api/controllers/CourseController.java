package com.ApiCourses.Courses.api.controllers;



import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.ICourseService;
import com.ApiCourses.Courses.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(path = "/course")
@AllArgsConstructor
public class CourseController {
    private final ICourseService CourseService;


    @GetMapping
    public ResponseEntity<Page<CourseResponse>>getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(required = false) SortType sortType){

        if (Objects.isNull(sortType)){
            sortType = SortType.NONE;
        }
        return ResponseEntity.ok(this.CourseService.getAll(page, size, sortType));
    }

}
