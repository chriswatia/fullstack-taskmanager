package com.warriors.taskmanager.Services;

import com.warriors.taskmanager.Models.Project;
import com.warriors.taskmanager.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired //Dependency Injection
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    //Create Project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Fetch all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    //Get Project By ID
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException ("Project not found"));
    }
}
