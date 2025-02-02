package org.example.rangohrmangement.controllers;

import org.example.rangohrmangement.enums.LeaveType;
import org.example.rangohrmangement.models.Leave;
import org.example.rangohrmangement.services.LeaveService;
import org.example.rangohrmangement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/leaves")
    public String listLeaves(Model model) {
        model.addAttribute("leaves", leaveService.getAllLeaves());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "leaves";
    }

    @PostMapping("/leaves/add")
    public String addLeave(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
                           @RequestParam String reason, @RequestParam LeaveType leaveType,
                           @RequestParam Long employeeId, Model model) {
        try {
            Leave leave = new Leave();
            leave.setStartDate(startDate);
            leave.setEndDate(endDate);
            leave.setReason(reason);
            leave.setLeaveType(leaveType);
            leave.setEmployee(employeeService.getEmployeeById(employeeId));
            leaveService.saveLeave(leave);
            return "redirect:/leaves";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "leaves"; // Kthehu në formularin e leaves me mesazhin e gabimit
        }
    }

    @GetMapping("/leaves/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Leave leave = leaveService.getLeaveById(id);
        model.addAttribute("leave", leave);
        return "edit-leave";
    }

    @PostMapping("/leaves/update/{id}")
    public String updateLeave(@PathVariable Long id, @RequestParam LocalDate startDate,
                              @RequestParam LocalDate endDate, @RequestParam String reason,
                              @RequestParam LeaveType leaveType) {
        Leave leave = leaveService.getLeaveById(id);
        leave.setStartDate(startDate);
        leave.setEndDate(endDate);
        leave.setReason(reason);
        leave.setLeaveType(leaveType);
        leaveService.saveLeave(leave);
        return "redirect:/leaves";
    }

    @GetMapping("/leaves/delete/{id}")
    public String deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return "redirect:/leaves";
    }
}