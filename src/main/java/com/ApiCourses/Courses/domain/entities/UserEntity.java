package com.ApiCourses.Courses.domain.entities;

import com.ApiCourses.Courses.utils.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(nullable = false, length = 70)
    private String email;
    @Column(nullable = false, length = 100)
    private String fullName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "user")
    private List<Course> courses;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "user")
    private List<Submission> submissions;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "user")
    private List<Enrollment> enrollments;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "sender")
    private List<Message> sentMessages;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "receiver")
    private List<Message> receivedMessages;


}
