package end.of.study.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "T_USER")
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class User extends IndexedComparableEntity {
    @Column(unique = true)
    private String name;

    @Column
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;
}
