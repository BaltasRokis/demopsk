package usecases;

import entities.Employee;
import lombok.Getter;
import lombok.Setter;
import persistence.EmployeeDAO;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named("employeeBean")
@ViewScoped  // Use ViewScoped for per-page state
public class EmployeeBean implements Serializable {

    @Inject
    private EmployeeDAO employeeDAO;

    @Getter
    @Setter // Add Setter
    private Employee employee;

    @Getter
    private List<Employee> employees;

    @PostConstruct
    public void init() {
        employees = employeeDAO.findAll(); // Always load the list of all employees
        loadSelectedEmployee(); // Separate method to load the specific employee
    }

    private void loadSelectedEmployee() {
        Map<String, String> requestParams = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();
        String employeeIdStr = requestParams.get("employeeId");

        if (employeeIdStr != null && !employeeIdStr.isEmpty()) {
            try {
                int employeeId = Integer.parseInt(employeeIdStr);
                employee = employeeDAO.find(employeeId);
                if (employee == null) {
                    // Handle the case where an employee with the given ID is not found
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "Employee not found", "No employee with ID: " + employeeIdStr));
                }
            } catch (NumberFormatException e) {
                // Handle invalid employeeId parameter
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid employee ID", "Please provide a valid employee ID."));
                employee = null; // Or set to a default, as appropriate
            }
        }
    }
}