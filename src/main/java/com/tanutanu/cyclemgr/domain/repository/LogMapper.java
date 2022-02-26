package com.tanutanu.cyclemgr.domain.repository;

import java.util.List;

import com.tanutanu.cyclemgr.domain.model.Log;

import org.apache.ibatis.annotations.Mapper;

/**
 * LogMapper
 */
@Mapper
public interface LogMapper {

    //操作
    //特定タスクのLog全件取得
    List<Log> findAllLogsByTaskId(String task_id); 

    //TaskLog予測値平均取得
    double getLogDeclAve(String task_id);

    //TaskLog実測値平均取得
    double getLogRealAve(String task_id);

    //ログ一件追加
    int createLog(Log log);

    //ログ全件削除
    int deleteAllLogsByTaskId(String task_id);

    //100件以上の場合に最古ログ1件削除
    int deleteOverfloatLogsByTaskId(String task_id);
}