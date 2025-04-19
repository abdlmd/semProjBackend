package com.example.semesterProjectBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Date createdAt;
    private String status;
    @ManyToOne
    @JoinColumn(name="requester_id")
    private Users posters;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Users volunteer;


    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;
}
