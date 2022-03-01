package com.tanutanu.cyclemgr.domain.service.Impl;

import java.util.List;

import com.tanutanu.cyclemgr.domain.model.Task;
import com.tanutanu.cyclemgr.domain.repository.TaskMapper;
import com.tanutanu.cyclemgr.domain.service.TaskService;

import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskMapper mapper; 
    
    public TaskServiceImpl(TaskMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int countUp(String task_id) {
        return mapper.countUpTask(task_id);
    }

    @Override
    public int create(Task task) {
        return mapper.createTask(task);
    }

    @Override
    public int delete(String task_id) {
        return mapper.deleteTask(task_id);
    }

    @Override
    public List<Task> readAllByUserId(String user_id) {
        return mapper.findAllTasksByUserId(user_id);
    }

    @Override
    public Task readById(String task_id) {
        return mapper.findTaskById(task_id);
    }

    @Override
    public int update(Task task) {
        return mapper.updateTask(task);
    }
    
}
