package com.tanutanu.cyclemgr.app.controller;

import java.util.List;

import com.tanutanu.cyclemgr.domain.model.Task;
import com.tanutanu.cyclemgr.domain.service.TaskService;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    //tasks
    //tasks/{taskId}
    //logs/{taskid}
    private TaskService service;

    public TaskController(TaskService taskService) {
        this.service = taskService;
    }

    @GetMapping("/user/{user_id}/tasks")
    public List<Task> getAllTask(String user_id) {
        return service.readAllByUserId(user_id);
    }

    @PostMapping("/tasks")
    public void addTask(@RequestBody Task task,Errors errors) {
        if (errors.hasErrors()) {
            throw new RuntimeException((Throwable)errors);
        }
        service.create(task);
    }

    @GetMapping("/tasks/{task_id}")
    public Task getTask(String task_id) {
        return service.readById(task_id);
    }

    @PostMapping("/tasks/{task_id}")
    public void useTask(String task_id) {
        service.countUp(task_id);
    }

    @PatchMapping("/tasks/{task_id}")
    public void updateTask(@RequestBody Task task,Errors errors) {
        if (errors.hasErrors()) {
            throw new RuntimeException((Throwable)errors);
        }
        service.update(task);
    }

    @DeleteMapping("/tasks/{task_id}")
    public void deleteTask(String task_id) {
        service.delete(task_id);
    }
}
