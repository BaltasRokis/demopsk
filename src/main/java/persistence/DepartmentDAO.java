package persistence;

import entities.Department;
import javax.ejb.Stateless;

@Stateless
public class DepartmentDAO extends BaseDAO<Department> {

    protected DepartmentDAO() {
        super(Department.class);
    }

    @Override
    public Department find(Object id) {
        return em.createQuery(
                        "SELECT d FROM Department d LEFT JOIN FETCH d.employees WHERE d.id = :id",
                        Department.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
