package cursoSpringBoot.service.security;

import cursoSpringBoot.domain.security.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User save(User user);
    boolean existsByUsername(String username);
}
