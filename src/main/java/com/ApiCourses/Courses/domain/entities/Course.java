package com.ApiCourses.Courses.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String courseName;
    @Column(nullable = false)
    @Lob
    private String description;
    @Column(nullable = false)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "instructor_id" ,referencedColumnName = "id")
    private UserEntity user;
}
