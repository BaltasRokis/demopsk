package entities;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PEN")
@NamedQueries({
        @NamedQuery(name = "Pen.findAll", query = "select a from Pen as a")
})
public class Pen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @JsonbProperty("name")
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

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
        Pen pen = (Pen) o;
        return Objects.equals(id, pen.id) &&
                Objects.equals(name, pen.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
