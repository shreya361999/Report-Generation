package com.example.demo.controller;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
	public Optional<Project> getProjectById(@PathVariable Long id) {
	return projectRepository.findById(id);		
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
	
	//fullreport
    @GetMapping("/fullReportExport")
	public ResponseEntity<ByteArrayResource> fullReport() throws IOException {
	    List<Project> projects = projectRepository.findAll();	    
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Projects");

	    //Column name given here
	    Row column = sheet.createRow(0);
	    column.createCell(0).setCellValue("Project ID");
	    column.createCell(1).setCellValue("Project Name");
	    column.createCell(2).setCellValue("Project Status");
	    column.createCell(3).setCellValue("Created By");
	    column.createCell(4).setCellValue("Creation Date");
	    column.createCell(5).setCellValue("Last Updated By");
	    column.createCell(6).setCellValue("Last Updated Date");
	    column.createCell(7).setCellValue("Region");
	    column.createCell(8).setCellValue("Comments");

	   //for row
	    int rowNum = 1;
	    for (Project project : projects) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(project.getProjectld());
	        row.createCell(1).setCellValue(project.getProjectName());
	        row.createCell(2).setCellValue(project.getProjectStatus());
	        row.createCell(3).setCellValue(project.getCreatedBy());
	        row.createCell(4).setCellValue(project.getCreationDate().toString());
	        row.createCell(5).setCellValue(project.getLastUpdatedBy());
	        row.createCell(6).setCellValue(project.getLastUpdatedDate().toString());
	        row.createCell(7).setCellValue(project.getRegion());
	        row.createCell(8).setCellValue(project.getComments());
	    }

	    // Set response headers
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    workbook.write(outputStream);
	    workbook.close();

	    ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

	    return ResponseEntity.ok()
	            .header("Content-Disposition", "attachment; filename=projectFullReport.xlsx")
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
    }

	   

	@DeleteMapping("/{id}")
    public String deleteProject(@PathVariable Long id) {
		projectRepository.deleteById(id);
		return "Employee deleted successfully";
	}
}
