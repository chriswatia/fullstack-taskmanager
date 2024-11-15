package com.warriors.taskmanager.Controllers;

import com.warriors.taskmanager.Models.Project;
import com.warriors.taskmanager.Models.Task;
import com.warriors.taskmanager.Models.TaskStatus;
import com.warriors.taskmanager.Services.ProjectService;
import com.warriors.taskmanager.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE, RequestMethod.OPTIONS}) // Enable CORS for this controller
@RequestMapping("api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final TaskService taskService;
    @Autowired
    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    //POST request to Create Project api/v1/projects
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    //GET request to retrieve all Projects api/v1/projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    //GET request to retrieve Project by id api/v1/projects
    @GetMapping("/{projectId}")
    public Project getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId);
    }

    // Update a project (PUT)
    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestBody Project projectDetails) {
        Optional<Project> updatedProject = projectService.updateProject(projectId, projectDetails);
        if (updatedProject.isPresent()) {
            return ResponseEntity.ok(updatedProject.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a project (DELETE)
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        boolean deleted = projectService.deleteProject(projectId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
