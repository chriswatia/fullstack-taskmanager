package com.warriors.taskmanager.Controllers;

import com.warriors.taskmanager.Models.Task;
import com.warriors.taskmanager.Models.TaskStatus;
import com.warriors.taskmanager.Models.TaskSummary;
import com.warriors.taskmanager.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    private TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Add a new task to a specific project
    @PostMapping("/projects/{projectId}")
    public ResponseEntity<Task> addTaskToProject(
            @PathVariable Long projectId,
            @RequestBody Task task
    ) {
        Task createdTask = taskService.addTaskToProject(projectId, task);
        return ResponseEntity.ok(createdTask);
    }

    // Retrieve all tasks for a specific project with optional filters and pagination
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<Page<Task>> getTasksForProject(
            @PathVariable Long projectId,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) Date dueDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Task> tasks = taskService.getTasksForProject(projectId, status, dueDate, page, size);
        return ResponseEntity.ok(tasks);
    }

    // Update a specific task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long taskId,
            @RequestBody Task updatedTask
    ) {
        Task task = taskService.updateTask(taskId, updatedTask);
        return ResponseEntity.ok(task);
    }

    // Delete a specific task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    // Get a summary of tasks grouped by status for a specific project
    @GetMapping("/projects/{projectId}/summary")
    public ResponseEntity<TaskSummary> getTaskSummaryForProject(@PathVariable Long projectId) {
        TaskSummary summary = taskService.getTaskSummaryForProject(projectId);
        return ResponseEntity.ok(summary);
    }
}

