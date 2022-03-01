package com.tanutanu.cyclemgr.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tanutanu.cyclemgr.domain.model.Task;
import com.tanutanu.cyclemgr.domain.repository.TaskMapper;
import com.tanutanu.cyclemgr.domain.service.Impl.TaskServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TaskServiceTest {
    @Mock
    private TaskMapper mapper;

    @InjectMocks
    private TaskServiceImpl service;
    
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("ReadTest...観点:一件のtaskをtaskIdから取得できる事")
    @Test
    public void testReadById(){
        Task task = new Task();
        task.setTask_id("T100");
        task.setTask_name("test");
        task.setTask_desc("task test");
        task.setUser_id("U100");
        task.setUse_count(1);
        task.setLast_update(new Timestamp(System.currentTimeMillis()));
        
        when(mapper.findTaskById(anyString())).thenReturn(task);

        Task actual = service.readById("T100");

        assertEquals(task.getTask_id(), actual.getTask_id());
        assertEquals(task.getTask_name(), actual.getTask_name());
        assertEquals(task.getTask_desc(), actual.getTask_desc());
        assertEquals(task.getUser_id(), actual.getUser_id());
        assertEquals(task.getUse_count(), actual.getUse_count());
        assertEquals(task.getLast_update(),actual.getLast_update());

        Mockito.verify(mapper,Mockito.times(1)).findTaskById("T100");
    }

    @DisplayName("ReadTest...観点:複数件のtaskをuserIdから取得できる事")
    @Test
    public void testReadAllByUserId(){
        List<Task> tasks = new ArrayList<>();
        String user_id = "U100";
        for (int i = 0; i < 3; i++) {
            Task task = new Task();
            String task_id = "T10" + String.valueOf(i);
            task.setTask_id(task_id);
            task.setTask_name("test");
            task.setTask_desc("task test");
            task.setUser_id(user_id);
            task.setUse_count(1);
            task.setLast_update(new Timestamp(System.currentTimeMillis()));

            tasks.add(task);
        }
        
        
        when(mapper.findAllTasksByUserId(anyString())).thenReturn(tasks);

        List<Task> actuals = service.readAllByUserId(user_id);

        assertEquals(3, actuals.size());

        Mockito.verify(mapper,Mockito.times(1)).findAllTasksByUserId(user_id);
    }

    @DisplayName("CreateTest...観点:taskを1件追加できる事")
    @Test
    public void testCreate() {
        Task task = new Task();
        task.setTask_name("test");
        task.setTask_desc("task test");
        task.setUser_id("U100");
        task.setUse_count(1);
        task.setLast_update(new Timestamp(System.currentTimeMillis()));
        
        when(mapper.createTask(Mockito.any(Task.class))).thenReturn(1);

        int actual = service.create(task);

        assertEquals(1,actual);

        Mockito.verify(mapper,Mockito.times(1)).createTask(task);
    }

    @DisplayName("UpdateTest...観点:taskを1件更新できる事")
    @Test
    public void testUpdate() {
        Task task = new Task();
        task.setTask_id("T100");
        task.setTask_name("updatetest");
        task.setTask_desc("task update test");
        task.setLast_update(new Timestamp(System.currentTimeMillis()));
        
        when(mapper.updateTask(Mockito.any(Task.class))).thenReturn(1);

        int actual = service.update(task);

        assertEquals(1,actual);

        Mockito.verify(mapper,Mockito.times(1)).updateTask(task);
    }

    @DisplayName("UpdateTest...観点:taskのcountUpを実行できる事")
    @Test
    public void testCountUp() {
        String task_id = "T100";
        
        when(mapper.countUpTask(anyString())).thenReturn(1);

        int actual = service.countUp(task_id);

        assertEquals(1,actual);

        Mockito.verify(mapper,Mockito.times(1)).countUpTask(task_id);
    }

    @DisplayName("DeleteTest...観点:taskのdeleteを実行できる事")
    @Test
    public void testDelete() {
        String task_id = "T100";
        
        when(mapper.deleteTask(anyString())).thenReturn(1);

        int actual = service.delete(task_id);

        assertEquals(1,actual);

        Mockito.verify(mapper,Mockito.times(1)).deleteTask(task_id);
    }

}
