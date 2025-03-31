package mybatis.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Employee {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer departmentId;
    private Department department;
    private List<Project> tasks;
}