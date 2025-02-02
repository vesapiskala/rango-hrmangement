package org.example.rangohrmangement.controllers;

import org.example.rangohrmangement.models.Department;
import org.example.rangohrmangement.models.Employee;
import org.example.rangohrmangement.services.DepartmentService;
import org.example.rangohrmangement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute Employee employee, @RequestParam Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        employee.setDepartment(department);
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "edit-employee";
    }

    @PostMapping("/employees/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {
        employee.setDepartment(departmentService.getDepartmentById(employee.getDepartment().getId()));
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping("/employees/report")
    public String showEmployeeReport(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employee-report";
    }
}