package com.tanutanu.cyclemgr.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Log
 */
@Getter
@Setter
public class Log {

    private String log_id;

    private String task_id;

    private int log_decl;

    private int log_real;
}