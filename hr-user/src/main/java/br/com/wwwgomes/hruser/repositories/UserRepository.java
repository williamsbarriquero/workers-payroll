package br.com.wwwgomes.hruser.repositories;

import br.com.wwwgomes.hruser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
