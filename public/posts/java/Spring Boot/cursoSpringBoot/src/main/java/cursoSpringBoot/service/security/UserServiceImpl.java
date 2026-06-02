package cursoSpringBoot.service.security;

import cursoSpringBoot.domain.security.Role;
import cursoSpringBoot.domain.security.RoleRepository;
import cursoSpringBoot.domain.security.User;
import cursoSpringBoot.domain.security.UserRepository;
import cursoSpringBoot.security.SpringSecurityConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, SpringSecurityConfig securityConfig, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>(); // Para almacenar los reles.

        // Se agrega a la lista el rol ROLE_USER, este es comun para todos los usuarios
        optionalRoleUser.ifPresent(roles::add);

        // Agregamos a la lista el rol ROLE_ADMIN si el usuario es admin.
        if (user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
