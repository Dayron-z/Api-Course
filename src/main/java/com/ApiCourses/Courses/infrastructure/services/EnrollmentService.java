package com.ApiCourses.Courses.infrastructure.services;


import com.ApiCourses.Courses.api.dto.request.used_request.EnrollmentRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.CourseBasicResponseToSpecificResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.EnrollmentBasicResponseToUser;
import com.ApiCourses.Courses.api.dto.response.custom_responses.UserBasicResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.EnrollmentResponse;
import com.ApiCourses.Courses.domain.entities.Course;
import com.ApiCourses.Courses.domain.entities.Enrollment;
import com.ApiCourses.Courses.domain.entities.UserEntity;
import com.ApiCourses.Courses.domain.repositories.CourseRepository;
import com.ApiCourses.Courses.domain.repositories.EnrollmentRepository;
import com.ApiCourses.Courses.domain.repositories.UserRepository;
import com.ApiCourses.Courses.infrastructure.abstract_services.IEnrollmentService;
import com.ApiCourses.Courses.utils.enums.SortType;
import com.ApiCourses.Courses.utils.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnrollmentService implements IEnrollmentService {
    @Autowired
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;


    @Override
    public EnrollmentResponse create(EnrollmentRequest request) {
        UserEntity user = this.userRepository.findById(request.getUserId()).orElseThrow(()->new BadRequestException("There are no users with the id provided"));

        Course course = this.courseRepository.findById(request.getCourseId()).orElseThrow(()-> new BadRequestException("There are no courses with the id provided"));

        Enrollment enrollment = this.requestToEntity(request);
        enrollment.setUser(user);
        enrollment.setCourse(course);

        return this.entityToResponse(this.enrollmentRepository.save(enrollment));
    }

    @Override
    public EnrollmentResponse get(Long id) {
        return this.entityToResponse(this.findById(id));
    }

    @Override
    public EnrollmentResponse update(EnrollmentRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Enrollment enrollment = this.findById(id);
        this.enrollmentRepository.delete(enrollment);
    }

    @Override
    public Page<EnrollmentResponse> getAll(int page, int size, SortType sort) {
        return null;
    }


    private Enrollment requestToEntity (EnrollmentRequest enrollmentRequest){
        return Enrollment.builder()
                .enrollmentDate(LocalDate.now())
                .build();
    }

    private EnrollmentResponse entityToResponse(Enrollment enrollment){
        var student = new UserBasicResponse();
        BeanUtils.copyProperties(enrollment.getUser(), student);

        var course = new CourseBasicResponseToSpecificResponse();
        BeanUtils.copyProperties(enrollment.getCourse(), course);

        var instructor = new UserBasicResponse();
        BeanUtils.copyProperties(enrollment.getCourse().getUser(), instructor);
        course.setInstructor(instructor);




        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .user(student)
                .course(course)
                .build();
    }

    private Enrollment findById(Long id){
        return this.enrollmentRepository.findById(id).orElseThrow(()-> new BadRequestException("There are no enrollments with the id provided"));
    }


    @Override
    public List<EnrollmentBasicResponseToUser> findByUserId(Long id) {
        return this.enrollmentRepository.findByUserId(id).stream().map(enrollment -> this.enrollmentToBasicResponse(enrollment)).collect(Collectors.toList());
    }

    private EnrollmentBasicResponseToUser enrollmentToBasicResponse(Enrollment enrollment){
        var course = new CourseBasicResponseToSpecificResponse();
        BeanUtils.copyProperties(enrollment.getCourse() , course);


        return EnrollmentBasicResponseToUser.builder()
                .id(enrollment.getId())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .course(course)
                .build();
    }

}
