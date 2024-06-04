package com.ApiCourses.Courses.domain.repositories;

import com.ApiCourses.Courses.domain.entities.Course;
import com.ApiCourses.Courses.domain.entities.Enrollment;
import com.ApiCourses.Courses.domain.entities.Subject;
import com.ApiCourses.Courses.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    public List<Enrollment> findByUserId(Long id);
    List<Enrollment> findByCourseId(Long courseId);


}
