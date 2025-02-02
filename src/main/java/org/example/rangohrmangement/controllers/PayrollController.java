package org.example.rangohrmangement.controllers;

import org.example.rangohrmangement.dtos.PayrollDTO;
import org.example.rangohrmangement.services.PayrollService;
import org.example.rangohrmangement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/payrolls")
    public String listPayrolls(Model model) {
        model.addAttribute("payrolls", payrollService.getAllPayrolls());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("payrollDTO", new PayrollDTO());
        return "payrolls";
    }

    @PostMapping("/payrolls/add")
    public String addPayroll(@ModelAttribute PayrollDTO payrollDTO, Model model) {
        try {
            payrollService.createPayroll(payrollDTO);
            return "redirect:/payrolls";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "payrolls"; // Kthehu në formularin e payrolls me mesazhin e gabimit
        }
    }

    @GetMapping("/payrolls/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("payroll", payrollService.getPayrollById(id));
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "edit-payroll";
    }

    @PostMapping("/payrolls/update/{id}")
    public String updatePayroll(@PathVariable Long id, @ModelAttribute PayrollDTO payrollDTO, Model model) {
        try {
            payrollService.updatePayroll(id, payrollDTO);
            return "redirect:/payrolls";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "payrolls"; // Kthehu në formularin e payrolls me mesazhin e gabimit
        }
    }

    @GetMapping("/payrolls/delete/{id}")
    public String deletePayroll(@PathVariable Long id) {
        payrollService.deletePayroll(id);
        return "redirect:/payrolls";
    }
}