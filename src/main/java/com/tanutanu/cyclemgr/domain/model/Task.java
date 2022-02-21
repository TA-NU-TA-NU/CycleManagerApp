package com.tanutanu.cyclemgr.domain.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * Task
 */

 @Getter
 @Setter
public class Task {
    private String task_id;

    private String task_name;

    private String task_desc;

    private String user_id;

    private int use_count;

    private Timestamp last_update;
}