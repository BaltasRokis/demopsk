package usecases;

import entities.Employee;
import lombok.Getter;
import lombok.Setter;
import persistence.EmployeeDAO;
import services.WorkHourService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.time.LocalTime;

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

    @Transactional
    public void generateWorkHours() {
        if (employee != null) {
            LocalTime[] hours = WorkHourService.generate();
            employee.setStartTime(hours[0]);
            employee.setEndTime(hours[1]);
            employeeDAO.update(employee);
        }
    }

}