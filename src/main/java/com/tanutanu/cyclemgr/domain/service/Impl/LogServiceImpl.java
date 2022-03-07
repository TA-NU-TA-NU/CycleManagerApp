package com.tanutanu.cyclemgr.domain.service.Impl;

import com.tanutanu.cyclemgr.domain.model.Log;
import com.tanutanu.cyclemgr.domain.repository.LogMapper;
import com.tanutanu.cyclemgr.domain.service.LogInfo;
import com.tanutanu.cyclemgr.domain.service.LogService;

import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService{

    public LogMapper mapper;

    public LogServiceImpl(LogMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int create(Log log) {
        int let;
        let = mapper.createLog(log);
        let += mapper.deleteOverfloatLogsByTaskId(log.getTask_id());
        return let;
    }

    @Override
    public int delete(String task_id) {
        return mapper.deleteAllLogsByTaskId(task_id);
    }

    @Override
    public LogInfo getLogInfo(String task_id) {
        LogInfo loginfo=new LogInfo();
        loginfo.setLoglist(mapper.findAllLogsByTaskId(task_id));
        loginfo.setLogDeclAve(mapper.getLogDeclAve(task_id));
        loginfo.setLogRealAve(mapper.getLogRealAve(task_id));
        return loginfo;
    }
    
}
