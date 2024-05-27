package com.ApiCourses.Courses.domain.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
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


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = false, mappedBy = "course")
    private List<Lesson> lessons;
}
