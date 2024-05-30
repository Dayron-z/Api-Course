package com.ApiCourses.Courses.api.dto.response.custom_responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageBasicResponse {
    private String message;
    private UserBasicResponse sender;
    private UserBasicResponse receiver;
}
