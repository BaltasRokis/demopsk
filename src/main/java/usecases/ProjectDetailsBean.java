package usecases;

import entities.Employee;
import entities.Project;
import lombok.Getter;
import lombok.Setter;
import persistence.EmployeeDAO;
import persistence.ProjectDAO;
import services.ProjectDisplayService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("projectDetailsBean")
public class ProjectDetailsBean implements Serializable {

    @Inject
    private ProjectDAO projectDAO;

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private ProjectDisplayService projectDisplayService;

    @Getter
    @Setter
    private Integer projectId;

    @Getter
    @Setter
    private Project project;

    @Getter
    @Setter
    private List<Integer> selectedEmployeeIds = new ArrayList<>();

    public void loadProjectDetails(){
        project = projectDAO.find(projectId);
    }

    public List<Employee> getAvailableEmployees() {
        return projectDAO.findEmployeesNotAssignedToProject(project.getId());
    }

    public List<Employee> getNewEmployees() {
        return projectDisplayService.getNewEmployees(project.getId());
    }

    @Transactional
    public void addEmployeesToProject() {
        var managedProject = projectDAO.find(project.getId());
        if(managedProject == null)
            return;

        for(Integer employeeId : selectedEmployeeIds) {
            var employee = employeeDAO.find(employeeId);
            if(employee != null){
                managedProject.getEmployees().add(employee);
                projectDisplayService.addNewEmployeeToProject(project.getId(), employee);
            }
        }
    }

    @Transactional
    public void removeEmployeeFromProject(Integer employeeId) {
        if(employeeId == null)
            return;
        var employee = employeeDAO.find(employeeId);
        var managedProject = projectDAO.find(project.getId());

        if (employee == null || !managedProject.getEmployees().contains(employee))
            return;
        managedProject.getEmployees().remove(employee);
        projectDisplayService.removeNewEmployeeToProject(project.getId(), employee);
    }

    @Transactional
    public String deleteProject() {
        if(!project.getEmployees().isEmpty())
            return null;
        projectDAO.removeById(project.getId());
        return "index.xhtml?faces-redirect=true";
    }
}
