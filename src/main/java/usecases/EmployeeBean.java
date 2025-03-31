package usecases;

import entities.Employee;
import lombok.Getter;
import lombok.Setter;
import persistence.EmployeeDAO;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("employeeBean")
public class EmployeeBean implements Serializable {

    @Inject
    private EmployeeDAO employeeDAO;

    @Getter
    @Setter
    private Employee employee;

    @Getter
    @Setter
    private Integer employeeId;

    public List<Employee> getEmployees() {
        return employeeDAO.findAll();
    }

    public void loadEmployeeDetails(){
        employee = employeeDAO.find(employeeId);
    }
}