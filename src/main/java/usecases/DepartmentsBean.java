package usecases;

import entities.Department;
import lombok.Getter;
import lombok.Setter;
import persistence.DepartmentDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named("departmentBean")
public class DepartmentsBean implements Serializable {

    @Inject
    private DepartmentDAO dao;

    @Getter
    @Setter
    private Department department = new Department();

    @Getter
    private List<Department> departments;

    @PostConstruct
    public void init() {
        loadDepartments();
    }

    @Transactional
    public void create() {
        dao.persist(department);
    }

    private void loadDepartments() {
        departments = dao.findAll();
    }
}
