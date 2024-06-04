package com.ApiCourses.Courses.api.controllers;


import com.ApiCourses.Courses.api.dto.request.used_request.LessonRequest;
import com.ApiCourses.Courses.api.dto.request.used_request.MessageRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.LessonResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.MessageResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.IMessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private final IMessageService iMessageService;

    @PostMapping
    public ResponseEntity<MessageResponse> create(@Validated @RequestBody MessageRequest messageRequest){
        return ResponseEntity.ok(this.iMessageService.create(messageRequest));
    }

    @GetMapping("/courses/{course_id}")
    public  ResponseEntity<List<MessageResponse>> findByCourse(@PathVariable("course_id") Long id){
        return ResponseEntity.ok(this.iMessageService.findByCourseId(id));
    }


    @GetMapping("/messages")
    public ResponseEntity<List<MessageResponse>> findByUsers(@RequestParam("sender_id") Long senderId, @RequestParam("receiver_id") Long receiverId){
        return ResponseEntity.ok(this.iMessageService.findBySenderIdAndReceiverId(senderId, receiverId));
    }
}
