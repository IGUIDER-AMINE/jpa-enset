package ma.enset.jpaenset.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.repositories.RoleRepository;
import ma.enset.jpaenset.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    //l'injection avec le constrecteur avec tous parameters, ne metter pas le constrecteur sans parameter
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findUserByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user= findUserByUserName(userName);
        Role role= findUserByRoleName(roleName);
        if(user.getRoles()!=null) {
            //l'orient bdirectionnelle
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        //userRepository.save(user); //on n'a pas besoin de fair ça car @Transactional
    }

    @Override
    public User authenticate(String userName, String password) {
        User user = userRepository.findByUsername(userName);
        if(user==null)
            throw new RuntimeException("Bad credentials");
        if(user.getPassword().equals(password))
            return user;
        throw new RuntimeException("Bad credentials");
    }
}
