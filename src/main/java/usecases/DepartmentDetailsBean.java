package usecases;

import entities.Department;
import entities.Employee;
import lombok.Getter;
import lombok.Setter;
import persistence.DepartmentDAO;
import persistence.EmployeeDAO;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;

@ViewScoped
@Named("departmentDetailsBean")
public class DepartmentDetailsBean implements Serializable {

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private DepartmentDAO departmentDAO;

    @Getter
    @Setter
    private Department department;

    @Getter
    @Setter
    private Integer departmentId;

    @Getter
    @Setter
    private Employee employee = new Employee();

    public void loadDepartmentData() {
        department = departmentDAO.find(departmentId);
    }

//    @Transactional
    public void createEmployee() {
        var managedDepartment = departmentDAO.find(department.getId());
        employee.setDepartment(managedDepartment);
        employeeDAO.persist(employee);
    }

    @Transactional
    public void deleteEmployee(Integer employeeId) {
        if(employeeId == null)
            return;

        var employee = employeeDAO.find(employeeId);
        if(employee == null)
            return;

        var assignedProjects = employee.getTasks();
        if(!assignedProjects.isEmpty()) {
            for(var assignedProject : assignedProjects) {
                assignedProject.getEmployees().remove(employee);
            }
        }

        employeeDAO.removeById(employee.getId());
    }

    @Transactional
    public String deleteDepartment() {
        if(departmentId == null)
            return null;
        var managedDep = departmentDAO.find(departmentId);
        if(managedDep == null || !managedDep.getEmployees().isEmpty())
            return null;

        departmentDAO.removeById(departmentId);
        return "index?faces-redirect=true";
    }
}
