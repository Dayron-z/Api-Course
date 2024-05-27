package com.ApiCourses.Courses.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "subjects")
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(length = 100, nullable = false)
    private String subjectTitle;
    @Column(nullable = false)
    @Lob
    private String text;
    @Column(nullable = false)
    private LocalDateTime dueDate;

    @OneToMany(fetch =  FetchType.LAZY)
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;



}
