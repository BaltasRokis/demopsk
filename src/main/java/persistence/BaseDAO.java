package persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class BaseDAO<T> {

    @PersistenceContext(unitName = "PostgrePU") // Container-managed EntityManager
    protected EntityManager em; // Protected so subclasses can access it

    private final Class<T> entityClass; // Store the entity class

    // Constructor to set the entity class
    protected BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Common DAO methods (can be overridden in subclasses if needed)
    public void persist(T entity) {
        em.persist(entity);
    }

    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

    public T find(Object id) {
        return em.find(entityClass, id);
    }

    public T update(T entity) {
        return em.merge(entity);
    }
    //find all method if the entity class has a named query findAll
    public List<T> findAll() {
        return em.createNamedQuery(entityClass.getSimpleName() + ".findAll", entityClass).getResultList();
    }
}
