package com.aminnorouzi.pma.controllers;

import com.aminnorouzi.pma.dto.ChartData;
import com.aminnorouzi.pma.dto.EmployeeProject;
import com.aminnorouzi.pma.entities.Project;
import com.aminnorouzi.pma.services.EmployeeService;
import com.aminnorouzi.pma.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProjectService proService;
    @Autowired
    EmployeeService empService;
    @Value("${version}")
    private String ver;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("versionNumber", ver);

        // we are querying the database for projects
        List<Project> projects = proService.getAll();
        model.addAttribute("projectsList", projects);

        List<ChartData> projectData = proService.getProjectStatus();

        // Lets convert projectData object into a json structure for use in javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        // [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]

        model.addAttribute("projectStatusCnt", jsonString);

        // we are querying the database for employees
        List<EmployeeProject> employeesProjectCnt = empService.employeeProjects();
        model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);

        return "main/home";
    }
}
