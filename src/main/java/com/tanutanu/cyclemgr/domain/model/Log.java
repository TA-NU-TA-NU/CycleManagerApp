package com.tanutanu.cyclemgr.domain.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Log
 */
@Getter
@Setter
public class Log {
    @NotNull
    private String log_id;

    @NotNull
    private String task_id;

    @NotNull
    private int log_decl;

    @NotNull
    private int log_real;
}