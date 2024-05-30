package com.ApiCourses.Courses.domain.repositories;


import com.ApiCourses.Courses.domain.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
