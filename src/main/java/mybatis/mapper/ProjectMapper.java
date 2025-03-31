package mybatis.mapper;

import java.util.List;
import mybatis.model.Project;
import org.mybatis.cdi.Mapper;

@Mapper
public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Project row);
    Project selectByPrimaryKey(Integer id);
    List<Project> selectAll();
    int updateByPrimaryKey(Project row);
}