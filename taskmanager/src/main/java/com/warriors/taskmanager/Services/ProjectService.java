package com.warriors.taskmanager.Services;

import com.warriors.taskmanager.Models.Project;
import com.warriors.taskmanager.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // Update a project
    public Optional<Project> updateProject(Long projectId, Project projectDetails) {
        Optional<Project> existingProject = projectRepository.findById(projectId);
        if (existingProject.isPresent()) {
            Project project = existingProject.get();
            project.setName(projectDetails.getName());
            project.setDescription(projectDetails.getDescription());
            return Optional.of(projectRepository.save(project));
        }
        return Optional.empty();
    }

    // Delete a project
    public boolean deleteProject(Long projectId) {
        if (projectRepository.existsById(projectId)) {
            projectRepository.deleteById(projectId);
            return true;
        }
        return false;
    }
}
