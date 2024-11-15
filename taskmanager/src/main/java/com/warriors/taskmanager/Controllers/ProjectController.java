package com.warriors.taskmanager.Controllers;

import com.warriors.taskmanager.Models.Project;
import com.warriors.taskmanager.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {
    private ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
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

    // Task management endpoints would go here
}
