package com.tanutanu.cyclemgr.domain.service;

import java.util.List;

import com.tanutanu.cyclemgr.domain.model.Task;

public interface TaskService {

    //タスクを一件取得
    Task readById(String task_id);

    //タスクを全件取得
    List<Task> readAllByUserId(String user_id);

    //タスクの追加
    int create(Task task);

    //タスクの更新
    int update(Task task);

    //タスクのカウントアップ
    int countUp(String task_id);

    //タスクの削除
    int delete(String task_id);

}
