package mybatis.mapper;

import java.util.List;
import mybatis.model.ProjectEmployee;
import org.mybatis.cdi.Mapper;


@Mapper
public interface ProjectEmployeeMapper {
    int insert(ProjectEmployee row);
    List<ProjectEmployee> selectAll();
    int deleteAllProjectsForEmployee(Integer employeeId);
    int deleteProjectForEmployee(ProjectEmployee row);
    int deleteAllEmployeesForProject(Integer projectId);
}