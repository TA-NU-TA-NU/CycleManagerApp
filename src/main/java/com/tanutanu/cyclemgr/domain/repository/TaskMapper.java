package com.tanutanu.cyclemgr.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tanutanu.cyclemgr.domain.model.*;

/**
 * TaskMapper
 */
@Mapper
public interface TaskMapper {

    //操作
    //ユーザーのTask全件取得
    List<Task> findAllTasksByUserId(String user_id); 

    //Task一件取得
    Task findTaskById(String task_id);
}