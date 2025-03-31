package services;

import entities.Employee;
import persistence.ProjectDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScoped
public class ProjectDisplayService implements Serializable {

    private Map<Integer, List<Employee>> newEmployees = new HashMap<>();

    public List<Employee> getNewEmployees(Integer id) {
        return newEmployees.get(id);
    }

    public void addNewEmployeeToProject(Integer id, Employee employee) {
        if(!newEmployees.containsKey(id)) {
            newEmployees.put(id, new ArrayList<>() {{
                add(employee);
            }});
        }else{
            newEmployees.get(id).add(employee);
        }
    }

    public void removeNewEmployeeToProject(Integer id, Employee employee) {
        if(!newEmployees.containsKey(id))
            return;
        newEmployees.get(id).remove(employee);
    }
}
