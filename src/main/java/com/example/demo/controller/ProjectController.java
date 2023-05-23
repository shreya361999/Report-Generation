package com.example.demo.controller;



import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;



@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	@PostMapping
	public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }
	
	@GetMapping
	public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isPresent()) {
            Project project = existingProject.get();
            project.setProjectName(updatedProject.getProjectName());
            project.setProjectStatus(updatedProject.getProjectStatus());
            project.setRegion(updatedProject.getRegion());
            project.setComments(updatedProject.getComments());
            project.setLastUpdatedBy(updatedProject.getLastUpdatedBy());
            project.setLastUpdatedDate(LocalDateTime.now());
            projectRepository.save(project);
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
	   

	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
