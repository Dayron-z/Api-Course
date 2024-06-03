package com.ApiCourses.Courses.api.controllers;


import com.ApiCourses.Courses.api.dto.request.used_request.LessonRequest;
import com.ApiCourses.Courses.api.dto.request.custom_request.UpdateLessonRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.CourseResponseToLessons;
import com.ApiCourses.Courses.api.dto.response.used_responses.LessonResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.ILessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/lesson")
@AllArgsConstructor
public class LessonController {
    private final ILessonService iLessonService;

    @PostMapping
    public ResponseEntity<LessonResponse> create(@Validated @RequestBody LessonRequest lessonRequest){
        return ResponseEntity.ok(this.iLessonService.create(lessonRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.iLessonService.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        this.iLessonService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> update(@PathVariable Long id,  @Validated @RequestBody UpdateLessonRequest lessonRequest){
        return ResponseEntity.ok(this.iLessonService.customUpdate(lessonRequest, id));
    }


    //IMPORTANTE
    @GetMapping(path = "/courses/{course_id}")
    public ResponseEntity<CourseResponseToLessons> findByCourse(@PathVariable("course_id") Long id) {
        return ResponseEntity.ok(this.iLessonService.findByCourse(id));
    }

}
