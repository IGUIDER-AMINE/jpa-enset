package ma.enset.jpaenset.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    private String userId;
    @Column(name = "USER_NAME",unique = true,length = 20)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//pas en lecture
    private String password;
    //EAGER => à chaque fois que je vais lui demander de charger un user il va charger automatiquement les roles de cet user
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
