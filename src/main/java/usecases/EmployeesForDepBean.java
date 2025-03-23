package usecases;

import entities.Department;
import entities.Employee;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.N;
import persistence.DepartmentDAO;
import persistence.EmployeeDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Named("employeesForDepBean")
@ViewScoped
public class EmployeesForDepBean implements Serializable {

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private DepartmentDAO departmentDAO;

    @Getter
    @Setter
    private Department department;

    @Getter
    @Setter
    private Employee employee = new Employee();

    @PostConstruct
    public void init() {
        if (department == null) {
            Map<String, String> requestParameters =
                    FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int depId = Integer.parseInt(requestParameters.get("depId"));
            department = departmentDAO.find(depId);
        }
    }

    @Transactional
    public void createEmployee() {
        employee.setDepartment(department);
        employeeDAO.persist(employee);
    }
}
