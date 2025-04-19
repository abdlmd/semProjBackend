package com.example.semesterProjectBackend.service;

import com.example.semesterProjectBackend.model.Tasks;
import com.example.semesterProjectBackend.model.Users;
import com.example.semesterProjectBackend.repository.TaskRepository;
import com.example.semesterProjectBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.security.Principal;

@Service
public class TaskService {
    @Autowired
    TaskRepository repo;
    @Autowired
    UserRepository userRepo;
    public ResponseEntity<?> getAllTasks() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);

    }

    public ResponseEntity<?> createTask(Tasks task, Principal principal) {
        String email=principal.getName();
        Users poster = userRepo.findByEmail(email).orElseThrow();
        task.setPosters(poster);
        return new ResponseEntity<>(repo.save(task),HttpStatus.OK);

    }

    public ResponseEntity<?> assignVolunteer(Tasks tasks, Principal principal) {
        String email= principal.getName();
        Users volunteer = userRepo.findByEmail(email).orElseThrow();
        tasks.setVolunteer(volunteer);
        return new ResponseEntity<>(repo.save(tasks),HttpStatus.OK);
    }

    public ResponseEntity<?> updateTask(Tasks tasks) {
        if(tasks.getVolunteer()!=null){
            tasks.setStatus("completed");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Task does not have a volunteer yet",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getMyTasks(Principal principal) {

        String email= principal.getName();
        Users poster=userRepo.findByEmail(email).orElseThrow();

        return new ResponseEntity<>(repo.findByPosters(poster),HttpStatus.OK);
    }

    public ResponseEntity<?> getVolunteeredTasks(Principal principal) {
        String email= principal.getName();
        Users volunteer=userRepo.findByEmail(email).orElseThrow();
        return new ResponseEntity<>(repo.findByVolunteer(volunteer),HttpStatus.OK);
    }

    public ResponseEntity<?> deleteTask(int id) {
        repo.deleteById(id);
        return new ResponseEntity<>("Task with ID:" + id + " deleted succesfully",HttpStatus.OK);
    }

    public ResponseEntity<?> searchTasks(String keyword) {
     return new ResponseEntity<>(repo.findByDescriptionContainingIgnoreCase(keyword),HttpStatus.OK);
    }
}
