package comp31.formdemo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import comp31.formdemo.model.Employee;
import comp31.formdemo.services.AdminService;
import comp31.formdemo.services.LoginService;

@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    LoginService loginService;
    AdminService adminService;

    public MainController(LoginService loginService, AdminService adminService) {
        this.loginService = loginService;
        this.adminService = adminService;
    }

    @GetMapping("/")
    String getRoot(Model model) {
        logger.info("---- At root.");
        Employee Employee = new Employee(); // Create backing object and
        model.addAttribute("employee", Employee); // send it to login form
        return "login-form";
    }

    @PostMapping("/login")
    public String getForm(Employee employee, Model model) {
        logger.info("---- At /login.");
        logger.info("---- " + employee.toString());
        Employee currentUser = loginService.findByUserId(employee.getUserId());

        String returnPage = loginService.validateUserInput(employee, currentUser, model);
        
        return returnPage;
    }

    @GetMapping("/add-employee")
    public String getAddEmployee(Model model) {
        logger.info("---- At /add-employee.");
        model.addAttribute("newEmployee", new Employee());
        return "new-employee-form";
    }

    @PostMapping("/add-employee")
    public String postAddEmployee(Model model, Employee employee) {
        
        loginService.addEmployee(employee);
        model.addAttribute("employee", new Employee());
        return "login-form";
    }

    @GetMapping("/findAllEmployees")
    public String getFindAllEmployees(Model model) {
        logger.info("---- At /findAllEmployees.");
        model.addAttribute("employees", adminService.findAllEmployees());
        return "departments/admin";
    }

}
