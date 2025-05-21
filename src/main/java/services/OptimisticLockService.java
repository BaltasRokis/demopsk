package services;

import entities.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class OptimisticLockService {

    @PersistenceContext
    private EntityManager em;

    public void simulateOptimisticLockConflict() {

        Employee emp1 = em.find(Employee.class, 7);


        EntityManager em2 = Persistence
                .createEntityManagerFactory("PostgrePU")
                .createEntityManager();

        em2.getTransaction().begin();
        Employee emp2 = em2.find(Employee.class, 7);


        emp2.setLastName("UpdatedByOther");
        em2.merge(emp2);
        em2.getTransaction().commit();
        em2.close();


        emp1.setLastName("UpdatedByUser1");

        try {
            em.merge(emp1);
            em.flush();
        } catch (OptimisticLockException e) {
            System.out.println("Caught OptimisticLockException: " + e.getMessage());
        }
    }
}
