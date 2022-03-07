package com.tanutanu.cyclemgr.domain.service;

import com.tanutanu.cyclemgr.domain.model.Log;

public interface LogService {

    //特定タスクのログ情報取得
    LogInfo getLogInfo(String task_id);

    //特定タスクのログを全件削除
    int delete(String task_id);

    //特定タスクのログを一件追加
    int create(Log log);

}
