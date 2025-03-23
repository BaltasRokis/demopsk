package persistence;

import entities.Project;
import javax.ejb.Stateless;

@Stateless
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
}
