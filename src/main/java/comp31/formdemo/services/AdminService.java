package comp31.formdemo.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import comp31.formdemo.model.Employee;
import comp31.formdemo.repositories.Accounts;

@Service
public class AdminService {

    Accounts accounts;

    public AdminService(Accounts accounts)
    {
        this.accounts = accounts;
    }

    public ArrayList<Employee> employees = accounts;

    //display the list of employees in a table.
    /*for (Employee employee : employees) {
        System.out.println(employee);
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Department</th>
            </tr>
            <tr>
                <td>employee.getFirstName()</td>
                <td>employee.getLastName()</td>
                <td>employee.getDepartment()</td>
            </tr>
        </table>
    }*/

    public ArrayList<Employee> findAllEmployees() {
        return accounts.findAllEmployees();
    }
}
