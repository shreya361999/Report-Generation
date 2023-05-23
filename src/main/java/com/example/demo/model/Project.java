package com.example.demo.model;


import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Project")
public class Project {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectld;
    
    @Column(name = "project_name")
    private String projectName;
    
    @Column(name = "project_status")
    private String projectStatus;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "createdBy")
    private String createdBy;
    
    @Column(name = "creation_date")
    @CreationTimestamp
    private Date creationDate;
    
    @Column(name = "last_updated_by")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String lastUpdatedBy;
    
    @Column(name = "last_updated_date")
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;
    
    private String region;
    private String comments;
	public Long getProjectld() {
		return projectld;
	}
	public void setProjectld(Long projectld) {
		this.projectld = projectld;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Project [projectld=" + projectld + ", projectName=" + projectName + ", projectStatus=" + projectStatus
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdatedBy=" + lastUpdatedBy
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", region=" + region + ", comments=" + comments + "]";
	}
	public Project(Long projectld, String projectName, String projectStatus, String createdBy, Date creationDate,
			String lastUpdatedBy, LocalDateTime lastUpdatedDate, String region, String comments) {
		super();
		this.projectld = projectld;
		this.projectName = projectName;
		this.projectStatus = projectStatus;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedDate = lastUpdatedDate;
		this.region = region;
		this.comments = comments;
	}
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    

}
