package com.warriors.taskmanager.Services;

import com.warriors.taskmanager.Models.Project;
import com.warriors.taskmanager.Models.Task;
import com.warriors.taskmanager.Models.TaskStatus;
import com.warriors.taskmanager.Models.TaskSummary;
import com.warriors.taskmanager.Repository.ProjectRepository;
import com.warriors.taskmanager.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Date;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    // Add a new task to a specific project
    public Task addTaskToProject(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));

        task.setProject(project);
        return taskRepository.save(task);
    }

    // Retrieve all tasks for a project with optional filters and pagination
    public Page<Task> getTasksForProject(Long projectId, TaskStatus status, Date dueDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (status != null && dueDate != null) {
            return taskRepository.findByProjectIdAndStatusAndDueDate(projectId, status, dueDate, pageable);
        } else if (status != null) {
            return taskRepository.findByProjectIdAndStatus(projectId, status, pageable);
        } else if (dueDate != null) {
            return taskRepository.findByProjectIdAndDueDate(projectId, dueDate, pageable);
        } else {
            return taskRepository.findByProjectId(projectId, pageable);
        }
    }

    // Update a task’s details
    public Task updateTask(Long taskId, Task updatedTask) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(existingTask);
    }

    // Delete a task
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));

        taskRepository.delete(task);
    }

    // Get a summary of tasks for a project grouped by status
    public TaskSummary getTaskSummaryForProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));

        long toDoCount = taskRepository.countByProjectIdAndStatus(projectId, TaskStatus.TO_DO);
        long inProgressCount = taskRepository.countByProjectIdAndStatus(projectId, TaskStatus.IN_PROGRESS);
        long doneCount = taskRepository.countByProjectIdAndStatus(projectId, TaskStatus.DONE);

        return new TaskSummary(project.getName(), toDoCount, inProgressCount, doneCount);
    }
}

