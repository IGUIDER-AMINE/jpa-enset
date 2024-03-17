package ma.enset.jpaenset.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String desc;
    private String roleName;
    @ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name="USERS_ROLES") // table de jointure
    private List<User> users= new ArrayList<>();
}
