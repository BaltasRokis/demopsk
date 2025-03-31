package mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Department {

    private Integer id;
    private String name;
    private List<Employee> employees;
}