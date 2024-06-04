package com.ApiCourses.Courses.domain.repositories;

import com.ApiCourses.Courses.domain.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRespository extends JpaRepository<Message, Long> {
    List<Message> findByCourseId(Long id);
    List<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
