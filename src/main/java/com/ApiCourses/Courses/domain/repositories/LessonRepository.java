package com.ApiCourses.Courses.domain.repositories;


import com.ApiCourses.Courses.domain.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository <Lesson, Long> {
}

