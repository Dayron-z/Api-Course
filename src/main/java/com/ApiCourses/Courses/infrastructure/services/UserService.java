package com.ApiCourses.Courses.infrastructure.services;

import com.ApiCourses.Courses.api.dto.request.UserRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.UserResponse;
import com.ApiCourses.Courses.domain.entities.UserEntity;
import com.ApiCourses.Courses.domain.repositories.UserRepository;
import com.ApiCourses.Courses.infrastructure.abstract_services.IUserService;
import com.ApiCourses.Courses.utils.enums.SortType;
import com.ApiCourses.Courses.utils.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private  final UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest request) {
        return null;
    }

    @Override
    public UserResponse get(Long id) {






        return null;
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sort) {

        if (page < 0){
            page = 0;
        }

        PageRequest pagination = null;

        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            // FIELD_BY_SORT is just an attribute in the interface that contains a string with the name of an entity attribute.
            // We use this attribute name to sort the results according to that rule.
            case ASC ->  pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return null;
    }



    private UserEntity find(Long id){
        return this.userRepository.findById(id).orElseThrow(()-> new BadRequestException("There are no users with the id provided"));
    }

    private UserResponse EntityToResponse(UserEntity user){
        UserResponse.builder()


                .build();

    }


}
