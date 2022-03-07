package com.tanutanu.cyclemgr.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tanutanu.cyclemgr.domain.model.Log;
import com.tanutanu.cyclemgr.domain.repository.LogMapper;
import com.tanutanu.cyclemgr.domain.service.Impl.LogServiceImpl;

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
public class LogServiceTest {
    @Mock
    private LogMapper mapper;

    @InjectMocks
    private LogServiceImpl service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("ReadTest...観点:ログ情報を取得してくる事")
    @Test
    public void testGetLogInfo() {
        String task_id = "T1";

        Log log = new Log();
        log.setLog_id("L1");
        log.setTask_id("T1");
        log.setLog_decl(1);
        log.setLog_real(2);
        List<Log> loglist = new ArrayList<>();
        loglist.add(log);

        when(mapper.findAllLogsByTaskId(anyString())).thenReturn(loglist);
        when(mapper.getLogDeclAve(anyString())).thenReturn(1.5);
        when(mapper.getLogRealAve(anyString())).thenReturn(2.22);

        LogInfo actual = service.getLogInfo(task_id);

        assertEquals(1, actual.getLoglist().size());
        assertEquals("delta:=0.01",Double.valueOf(1.5),Double.valueOf(actual.getLogDeclAve()),Double.valueOf(0.01));
        assertEquals("delta:=0.01",Double.valueOf(2.22),Double.valueOf(actual.getLogRealAve()),Double.valueOf(0.001));
        
        Mockito.verify(mapper,Mockito.times(1)).findAllLogsByTaskId(task_id);
        Mockito.verify(mapper,Mockito.times(1)).getLogDeclAve(task_id);
        Mockito.verify(mapper,Mockito.times(1)).getLogRealAve(task_id);
    }

    @DisplayName("CreateTest...観点:ログ情報を1件生成してくる事")
    @Test
    public void testCreate() {
        Log log = new Log();
        log.setLog_id("L1");
        log.setTask_id("T1");
        log.setLog_decl(1);
        log.setLog_real(2);

        when(mapper.createLog(Mockito.any(Log.class))).thenReturn(1);
        when(mapper.deleteOverfloatLogsByTaskId(anyString())).thenReturn(1);

        int actual = service.create(log);

        assertEquals(2, actual);

        Mockito.verify(mapper,Mockito.times(1)).createLog(log);
        Mockito.verify(mapper,Mockito.times(1)).deleteOverfloatLogsByTaskId(log.getTask_id());
    }

    @DisplayName("DeleteTest...観点:ログ情報を全件削除する事")
    @Test
    public void testDelete() {
        String task_id = "T1";

        when(mapper.deleteAllLogsByTaskId(anyString())).thenReturn(1);

        int actual = service.delete(task_id);

        assertEquals(1, actual);

        Mockito.verify(mapper,Mockito.times(1)).deleteAllLogsByTaskId(task_id);
    }

}
