package com.ApiCourses.Courses.domain.repositories;

import com.ApiCourses.Courses.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
