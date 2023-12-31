package comp31.formdemo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import comp31.formdemo.model.Employee;
import comp31.formdemo.repositories.Accounts;

@Service
public class LoginService {

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    Accounts accounts;

    public LoginService(Accounts accounts) {
        this.accounts = accounts;

        // Hardcode initial set of users
        String[] userIds = {
            "sam", "sally", "ollie", "olivia", "rachel", "ralph", "abbie", "arthur"
        };
        String[] departments = {
            "sales", "sales", "orders", "orders", "repairs", "repairs", "admin", "admin"
        };
        String[] firstNames = {
            "Sam", "Sally", "Ollie", "Olivia", "Rachel", "Ralph", "Abbie", "Arthur"
        };
        String[] lastNames = {
            "Smith", "Johnson", "Brown", "Davis", "White", "Jones", "Wilson", "Miller"
        };

        for (int i = 0; i < userIds.length; i++) {
            String userId = userIds[i];
            String department = departments[i];
            String firstName = firstNames[i];
            String lastName = lastNames[i];
            
            addEmployee(userId, department, firstName, lastName);
        }

    }

    public void addEmployee(String userId, String department, String firstName, String lastName) {
        logger.info("Adding user: " + userId);
        logger.info("with department: " + department);
        logger.info("with first name: " + firstName);
        Employee employee = new Employee();
        employee.setUserId(userId);
        employee.setDepartment(department);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPassword(userId); // Set password to be the same as the userId
        
        accounts.add(employee);
    }

    public void addEmployee(Employee Employee) {
        accounts.add(Employee);
    }

    public Employee findByUserId(String userId) {
        return accounts.findByUserId(userId);
    }

    public String validateUserInput(Employee employee, Employee currentUser, Model model) {
        String returnPage;
        
            if(employee.getUserId() == null || employee.getUserId().isEmpty() || employee.getPassword() == null || employee.getPassword().isEmpty()){
                model.addAttribute("employee", employee);
                returnPage = "login-form";
            }
            // ...
        else if(employee.getUserId().equals("admin")){
            //model.addAttribute("newEmployee", new Employee());
            returnPage = "departments/admin";
        }
        //else if (currentUser.getUserId().equals(employee.getPassword()) && currentUser.getPassword().equals(employee.getPassword())) {
        else if (currentUser.getUserId().equals("ollie")) {
            model.addAttribute("employee", currentUser);
            returnPage = "departments/" + currentUser.getDepartment();
        }
        else if (currentUser == null) {
            model.addAttribute("employee", employee);
            returnPage = "login-form";
        } 
        else {
            model.addAttribute("employee", currentUser);
            returnPage = "welcome";
        }
            return returnPage;
    }

}
