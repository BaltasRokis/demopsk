package mybatis.mapper;

import java.util.List;
import mybatis.model.Employee;
import org.mybatis.cdi.Mapper;

@Mapper
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Employee row);
    Employee selectByPrimaryKey(Integer id);
    List<Employee> selectAll();
    int updateByPrimaryKey(Employee row);
}