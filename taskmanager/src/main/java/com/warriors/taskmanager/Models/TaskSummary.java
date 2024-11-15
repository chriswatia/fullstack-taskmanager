package com.warriors.taskmanager.Models;

public class TaskSummary {
    private String projectName;
    private long toDoCount;
    private long inProgressCount;
    private long doneCount;

    // Constructor, Getters, and Setters

    public TaskSummary() {
    }

    public TaskSummary(String projectName, long toDoCount, long inProgressCount, long doneCount) {
        this.projectName = projectName;
        this.toDoCount = toDoCount;
        this.inProgressCount = inProgressCount;
        this.doneCount = doneCount;
    }
}

