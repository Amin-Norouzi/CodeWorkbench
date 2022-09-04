package com.aminnorouzi.pma.controllers;

import com.aminnorouzi.pma.dao.EmployeeRepository;
import com.aminnorouzi.pma.dao.ProjectRepository;
import com.aminnorouzi.pma.dto.ChartData;
import com.aminnorouzi.pma.dto.EmployeeProject;
import com.aminnorouzi.pma.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

//        model.addAttribute("versionNumber", ver);

        // we are querying the database for projects
        List<Project> projects = projectRepo.findAll();
        model.addAttribute("projectsList", projects);

        List<ChartData> projectData = projectRepo.getProjectStatus();

        // Lets convert projectData object into a json structure for use in javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        // [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]

        model.addAttribute("projectStatusCnt", jsonString);

        // we are querying the database for employees
        List<EmployeeProject> employeesProjectCnt = employeeRepo.employeeProjects();
        model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);

        return "main/home";
    }
}
