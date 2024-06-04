package com.ApiCourses.Courses.domain.repositories;

import com.ApiCourses.Courses.api.dto.response.used_responses.SubmissionResponse;
import com.ApiCourses.Courses.domain.entities.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    public List<Submission> findBySubjectId(Long id);
    public List<Submission> findByUserId(Long id);
}
