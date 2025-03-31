package persistence;

import entities.Employee;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeDAO extends BaseDAO<Employee> {

    protected EmployeeDAO() {
        super(Employee.class);
    }

    @Override
    public Employee find(Object id) {
        return em.createQuery(
                        "SELECT e FROM Employee e LEFT JOIN FETCH e.department LEFT JOIN FETCH e.tasks WHERE e.id = :id",
                        Employee.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
