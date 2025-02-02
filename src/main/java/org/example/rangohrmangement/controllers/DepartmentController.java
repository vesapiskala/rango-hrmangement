package org.example.rangohrmangement.controllers;

import org.example.rangohrmangement.models.Department;
import org.example.rangohrmangement.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "departments";
    }

    @PostMapping("/departments/add")
    public String addDepartment(@RequestParam String name) {
        Department department = new Department();
        department.setName(name);
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/departments/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "edit-department";
    }

    @PostMapping("/departments/update/{id}")
    public String updateDepartment(@PathVariable Long id, @RequestParam String name) {
        Department department = departmentService.getDepartmentById(id);
        department.setName(name);
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}