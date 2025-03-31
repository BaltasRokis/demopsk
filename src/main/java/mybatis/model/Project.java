package mybatis.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Project {

    private Integer id;
    private String description;
    private String title;
    private List<Employee> employees;
}