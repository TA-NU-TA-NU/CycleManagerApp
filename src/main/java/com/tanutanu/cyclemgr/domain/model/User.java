package com.tanutanu.cyclemgr.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * User
 */
@Getter
@Setter
public class User {

    private String user_id;

    private String user_name;

    private String password;

    private String role_name;
}