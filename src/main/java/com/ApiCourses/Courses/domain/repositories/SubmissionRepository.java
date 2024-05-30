package com.ApiCourses.Courses.domain.repositories;

import com.ApiCourses.Courses.domain.entities.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
