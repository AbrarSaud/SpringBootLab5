package com.example.projecttrackersystem.Controller;

import com.example.projecttrackersystem.Api.ApiResponse;
import com.example.projecttrackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    // Display all project .
    @GetMapping("/get")
    public ArrayList<Project> getAllProject() {
        return projects;
    }

    // Create a new project
    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project project) {
        projects.add(project);
        return new ApiResponse("added successfully!!");
    }

    // Update a project
    @PutMapping("/update/{id}")
    public ApiResponse updateProject(@PathVariable int id, @RequestBody Project project) {
        for (Project p : projects) {
            if (p.getId() == id) {
                p.setTitle(project.getTitle());
                p.setStatus(project.getStatus());
                p.setDescription(project.getDescription());
                p.setCompanyName(project.getCompanyName());
                return new ApiResponse("updated successfully!!");
            }
        }
        return new ApiResponse("Project not found!");
    }

    // Delete a project
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteProject(@PathVariable int id) {
        for (Project p : projects) {
            if (p.getId() == id) {
                projects.remove(p);
                return new ApiResponse("deleted successfully!");
            }
        }
        return new ApiResponse("Project not found!");
    }

    // Change the project status as done or not done
    @PutMapping("/change/{id}")
    public ApiResponse changeStatus(@PathVariable int id, @RequestParam String status) {
        for (Project p : projects) {
            if (p.getId() == id) {
                if (p.getStatus().equalsIgnoreCase("Done")) {
                    return new ApiResponse("Project is  Done!!");
                } else if (p.getStatus().equalsIgnoreCase("NOT Done")) {
                    p.setStatus(status);
                    return new ApiResponse("Project changed successfully to ' Done'");
                }
            }
        }
        return new ApiResponse("Project not found!");
    }

    //    Search for a project by given title
    @GetMapping("/search")
    public ArrayList<Project> searchProjectByTitle(@RequestParam String title) {
        ArrayList<Project> projectByTitle = new ArrayList<>();

        for (Project p : projects) {
            if (p.getTitle().equalsIgnoreCase(title)) {
                projectByTitle.add(p);
            }
        }
        return projectByTitle;
    }

    //    Display All project for one company by companyName.
    @GetMapping("/get-by-CompanyName")
    public ArrayList<Project> gerProjectByCompanyName(@RequestParam String companyName) {
        ArrayList<Project> projectByCompanyName = new ArrayList<>();

        for (Project p : projects) {
            if (p.getCompanyName().equalsIgnoreCase(companyName)) {
                projectByCompanyName.add(p);
            }
        }
        return projectByCompanyName;
    }
}
