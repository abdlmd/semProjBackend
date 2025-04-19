package com.example.semesterProjectBackend.controller;

import com.example.semesterProjectBackend.model.Tasks;
import com.example.semesterProjectBackend.model.Users;
import com.example.semesterProjectBackend.service.TaskService;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTasks(){
        return service.getAllTasks();
    }
    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestBody Tasks task, Principal principal){
        return service.createTask(task,principal);

    }
    @PutMapping("/tasks")
    public ResponseEntity<?> assignVolunteer(@RequestBody Tasks tasks, Principal principal){
        return service.assignVolunteer(tasks,principal);
    }
    @PutMapping("/taskUpdate")
    public ResponseEntity<?> updateTask(@RequestBody Tasks tasks){
        return service.updateTask(tasks);
    }
    @GetMapping("/myTasks")
    public ResponseEntity<?> getMyTasks(Principal principal){
        return service.getMyTasks(principal);
    }
    @GetMapping("/volunteeredTasks")
    public ResponseEntity<?> getVolunteeredTasks(Principal principal)
    {
        return service.getVolunteeredTasks(principal);
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id){
        return service.deleteTask(id);
    }
    @GetMapping("/task/{keyword}")
    public ResponseEntity<?> searchTasks(@PathVariable String keyword){
        return service.searchTasks(keyword);
    }


}
