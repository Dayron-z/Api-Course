package com.ApiCourses.Courses.domain.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "subject")
public class Subject {
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

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL, mappedBy = "subject")
    private List<Submission> submissions;


}
