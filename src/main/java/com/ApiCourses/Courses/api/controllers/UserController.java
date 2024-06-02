package com.ApiCourses.Courses.api.controllers;


import com.ApiCourses.Courses.api.dto.request.UserRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.UserResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final IUserService iUserService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Validated @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(this.iUserService.create(userRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.iUserService.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        this.iUserService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,  @Validated @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(this.iUserService.update(userRequest, id));
    }

}
