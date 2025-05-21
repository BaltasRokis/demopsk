package entities;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "EMPLOYEE")
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "select a from Employee as a")
})
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @JsonbProperty("first_name")
    @Size(max = 100)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @JsonbProperty("last_name")
    @Size(max = 100)
    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    @JsonbTransient
    private Department department;

    @ManyToMany(mappedBy = "employees")
    @JsonbTransient
    private List<Project> tasks = new ArrayList<>();

    @Column(name = "START_TIME")
    @JsonbTransient
    private LocalTime startTime;

    @Column(name = "END_TIME")
    @JsonbTransient
    private LocalTime endTime;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    @JsonbTransient
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
