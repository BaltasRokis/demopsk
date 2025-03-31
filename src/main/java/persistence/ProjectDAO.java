package persistence;

import entities.Employee;
import entities.Project;
import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ProjectDAO extends BaseDAO<Project> {

    protected ProjectDAO() {
        super(Project.class);
    }

    @Override
    public Project find(Object id) {
        return em.createQuery(
                        "SELECT d FROM Project d LEFT JOIN FETCH d.employees WHERE d.id = :id",
                        Project.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Employee> findEmployeesNotAssignedToProject(Integer projectId) {
        if (projectId == null) {
            return Collections.emptyList();
        }
        return em.createQuery(
                        "SELECT e FROM Employee e WHERE NOT EXISTS (SELECT p FROM e.tasks p WHERE p.id = :projectId)",
                Employee.class)
            .setParameter("projectId", projectId)
            .getResultList();
    }
}
