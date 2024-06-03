package com.ApiCourses.Courses.domain.repositories;


import com.ApiCourses.Courses.api.dto.response.used_responses.SubjectResponse;
import com.ApiCourses.Courses.domain.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    public List<Subject> findBylessonId(Long id);
}
