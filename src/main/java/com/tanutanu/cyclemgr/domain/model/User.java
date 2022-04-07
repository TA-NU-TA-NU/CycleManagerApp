package com.tanutanu.cyclemgr.domain.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * User
 */
@Getter
@Setter
public class User {

    @NotNull
    private String user_id;

    @NotNull
    private String user_name;

    @NotNull
    private String password;

    @NotNull
    private String role_name;
}