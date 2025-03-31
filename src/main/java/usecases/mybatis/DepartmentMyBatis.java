package usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import mybatis.mapper.DepartmentMapper;
import mybatis.model.Department;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("departmentMyBatis")
public class DepartmentMyBatis implements Serializable {

    @Inject
    private DepartmentMapper departmentMapper;

    @Getter
    @Setter
    private Department department = new Department();

    public List<Department> loadAllDepartments() {
        return departmentMapper.selectAllWithEmployees();
    }

    @Transactional
    public String addDepartment() {
        departmentMapper.insert(department);
        return "/mybatis/departments?faces-redirect=true";
    }
}
