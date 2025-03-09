package com.example.projecttrackersystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    public  int id;
    public String title ;
    public String description;
    public String status;
    public String companyName;
}
