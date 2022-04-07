package com.tanutanu.cyclemgr.app.controller;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tanutanu.cyclemgr.domain.model.Task;
import com.tanutanu.cyclemgr.domain.service.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.Errors;

@RunWith(SpringRunner.class)
public class TaskControllerTest {
    @Mock
    private TaskService service;

    @InjectMocks
    private TaskController controller;
    
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("ReadTest...観点:任意のユーザーのタスク情報を取得してくる事")
    @Test
    public void testGetAllTask(){
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

        when(service.readAllByUserId(user_id)).thenReturn(tasks);

        List<Task> actuals = controller.getAllTask(user_id);

        assertEquals(3,actuals.size());

        Mockito.verify(service,Mockito.times(1)).readAllByUserId(user_id);
    }

    @DisplayName("CreateTest...観点:任意のタスク情報を追加できる事")
    @Test
    public void testAddTask(){
        String user_id = "U100";
        Task task = new Task();
        task.setTask_name("test");
        task.setTask_desc("task test");
        task.setUser_id(user_id);
        task.setUse_count(1);
        task.setLast_update(new Timestamp(System.currentTimeMillis()));

        Errors error = mock(Errors.class);
        controller.addTask(task, error);

        Mockito.verify(service,Mockito.times(1)).create(task);
    }

    @DisplayName("CreateTest...観点:異常値に対してエラーを返す事")
    @Test
    public void testAddTaskAbnormal() {
        Task task = new Task();
        task.setTask_desc("test");
        Errors error = mock(Errors.class);
        when(error.hasErrors()).thenReturn(true);
        
        assertThrows(RuntimeException.class, () -> controller.addTask(task, error));
    }

    @DisplayName("ReadTest...特定のタスクをIDから取得できる事")
    @Test
    public void testGetTask() {
        String task_id = "T1";

        Task task = new Task();
        task.setTask_id(task_id);
        task.setTask_name("test");
        task.setTask_desc("task test");
        task.setUser_id("U100");
        task.setUse_count(1);
        task.setLast_update(new Timestamp(System.currentTimeMillis()));

        when(service.readById(task_id)).thenReturn(task);

        Task actual = controller.getTask(task_id);

        assertEquals(task.getTask_id(), actual.getTask_id());
        assertEquals(task.getTask_name(), actual.getTask_name());
        assertEquals(task.getTask_desc(), actual.getTask_desc());
        assertEquals(task.getUser_id(), actual.getUser_id());
        assertEquals(task.getUse_count(), actual.getUse_count());
        assertEquals(task.getLast_update(), actual.getLast_update());

        Mockito.verify(service,Mockito.times(1)).readById(task_id);
    }

    @DisplayName("UpdateTest...観点:任意のタスクのカウントアップができる事")
    @Test
    public void testUseTask(){
        String task_id = "T1";
        
        controller.useTask(task_id);

        Mockito.verify(service,Mockito.times(1)).countUp(task_id);
    }

    @DisplayName("UpdateTest...観点:任意のタスク情報を更新できる事")
    @Test
    public void testUpdateTask(){
        String user_id = "U100";
        Task task = new Task();
        task.setTask_name("test");
        task.setTask_desc("task test");
        task.setUser_id(user_id);
        task.setUse_count(1);
        task.setLast_update(new Timestamp(System.currentTimeMillis()));

        Errors error = mock(Errors.class);
        controller.updateTask(task, error);

        Mockito.verify(service,Mockito.times(1)).update(task);
    }

    @DisplayName("UpdateTest...観点:異常値に対してエラーを返す事")
    @Test
    public void testUpdateTaskAbnormal() {
        Task task = new Task();
        task.setTask_desc("test");
        Errors error = mock(Errors.class);
        when(error.hasErrors()).thenReturn(true);
        
        assertThrows(RuntimeException.class, () -> controller.updateTask(task, error));
    }

    @DisplayName("DeleteTest...観点:任意のタスク一件が正常に削除できる事")
    @Test
    public void testDeleteTask() {
        String task_id = "T1";

        controller.deleteTask(task_id);

        Mockito.verify(service,Mockito.times(1)).delete(task_id);
    }

}
