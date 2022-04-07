package com.tanutanu.cyclemgr.domain.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Task
 */

 @Getter
 @Setter
public class Task {
    @NotNull
    private String task_id;

    @NotNull
    private String task_name;

    private String task_desc;

    @NotNull
    private String user_id;

    @NotNull
    private int use_count;

    @NotNull
    private Timestamp last_update;
}