package com.ljh.activitidemo;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class JmpCmd implements Command<Void> {
    private String taskId;
    private String exeId;
    private String source;
    private String target;

    @Override
    public Void execute(CommandContext commandContext) {
        return null;
    }
}
