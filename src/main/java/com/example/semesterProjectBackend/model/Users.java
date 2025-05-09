package com.example.semesterProjectBackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "posters")
    @JsonManagedReference("posted-tasks")
    List<Tasks> postedTasks;

    @OneToMany(mappedBy = "volunteer")
    @JsonManagedReference("volunteered-tasks")
    List<Tasks> workedTasks;

}
