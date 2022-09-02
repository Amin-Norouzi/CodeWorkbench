package com.aminnorouzi.pma.controllers;

import com.aminnorouzi.pma.dao.EmployeeRepository;
import com.aminnorouzi.pma.dao.ProjectRepository;
import com.aminnorouzi.pma.entities.Employee;
import com.aminnorouzi.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping
    public String displayProject(Model model) {
        List<Project> projects = projectRepo.findAll();
        model.addAttribute("projects", projects);

        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        List<Employee> employees = employeeRepo.findAll();

        model.addAttribute("allEmployees", employees);
        model.addAttribute("project", new Project());

        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project, Model model) {
        projectRepo.save(project);
        
        return "redirect:/projects/new";
    }
}
