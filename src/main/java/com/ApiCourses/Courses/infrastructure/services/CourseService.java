package com.ApiCourses.Courses.infrastructure.services;


import com.ApiCourses.Courses.api.dto.request.CourseRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.ICourseService;
import com.ApiCourses.Courses.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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









}
