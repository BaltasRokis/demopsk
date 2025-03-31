package mybatis.mapper;

import java.util.List;
import mybatis.model.Department;
import org.mybatis.cdi.Mapper;

@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Department row);
    Department selectByPrimaryKey(Integer id);
    List<Department> selectAll();
    int updateByPrimaryKey(Department row);
    List<Department> selectAllWithEmployees();
}