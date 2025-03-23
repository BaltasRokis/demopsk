package usecases;

import entities.Employee;
import entities.Project;
import lombok.Getter;
import lombok.Setter;
import persistence.EmployeeDAO;
import persistence.ProjectDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Named("employeesForProjBean")
@SessionScoped
public class EmployeesForProjBean implements Serializable {

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private ProjectDAO projectDAO;

    @Getter
    @Setter
    private Project project;

    @Getter
    @Setter
    private Employee employee = new Employee();

    @PostConstruct
    public void init() {
        if (project == null) {
            Map<String, String> requestParameters =
                    FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int projectId = Integer.parseInt(requestParameters.get("projectId"));
            project = projectDAO.find(projectId);
        }
    }

    @Transactional
    public void createEmployee() {
        employee.getTasks().add(project);
        employeeDAO.persist(employee);
    }
}
