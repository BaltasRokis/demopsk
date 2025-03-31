package usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import mybatis.mapper.EmployeeMapper;
import mybatis.mapper.ProjectEmployeeMapper;
import mybatis.model.Employee;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("employeeMyBatis")
public class EmployeeMyBatis implements Serializable {

    @Inject
    private EmployeeMapper employeeMapper;

    @Inject
    private ProjectEmployeeMapper projectEmployeeMapper;

    @Getter
    @Setter
    private Employee employee;

    public List<Employee> loadAllEmployees(){
        return employeeMapper.selectAll();
    }

    @Transactional
    public void insertEmployee(){
        employeeMapper.insert(employee);
    }

    @Transactional
    public String deleteEmployee(Integer id){
        projectEmployeeMapper.deleteAllProjectsForEmployee(id);
        employeeMapper.deleteByPrimaryKey(id);
        return "/mybatis/employees?faces-redirect=true";
    }
}
