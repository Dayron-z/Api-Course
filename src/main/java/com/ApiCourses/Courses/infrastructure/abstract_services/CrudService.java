package com.ApiCourses.Courses.infrastructure.abstract_services;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface CrudService<RQ,RS, ID>{
    public RS create(RQ request);
    public RS get(ID id);
    public RS update(RQ request, ID id);
    public void delete(ID id);
    public Page<RS> getAll(int page, int size, )


}
