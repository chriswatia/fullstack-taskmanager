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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public long getToDoCount() {
        return toDoCount;
    }

    public void setToDoCount(long toDoCount) {
        this.toDoCount = toDoCount;
    }

    public long getInProgressCount() {
        return inProgressCount;
    }

    public void setInProgressCount(long inProgressCount) {
        this.inProgressCount = inProgressCount;
    }

    public long getDoneCount() {
        return doneCount;
    }

    public void setDoneCount(long doneCount) {
        this.doneCount = doneCount;
    }

    @Override
    public String toString() {
        return "TaskSummary{" +
                "projectName='" + projectName + '\'' +
                ", toDoCount=" + toDoCount +
                ", inProgressCount=" + inProgressCount +
                ", doneCount=" + doneCount +
                '}';
    }
}


