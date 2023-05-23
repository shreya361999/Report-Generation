package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
	List<Project> findByCreationDateBetween(Date date1, Date date2);

}
