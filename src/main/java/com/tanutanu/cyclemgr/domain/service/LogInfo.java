package com.tanutanu.cyclemgr.domain.service;

import java.util.List;

import com.tanutanu.cyclemgr.domain.model.Log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInfo {

    private List<Log> loglist;

    private double logDeclAve;

    private double logRealAve;
}
