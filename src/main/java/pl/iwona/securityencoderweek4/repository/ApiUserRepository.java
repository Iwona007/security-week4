package pl.iwona.securityencoderweek4.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.iwona.securityencoderweek4.model.ApiUser;
import pl.iwona.securityencoderweek4.model.Role;

public interface ApiUserRepository extends JpaRepository<ApiUser, Long> {
    Optional<ApiUser> findAllByUsername(String username);
    ApiUser findByRole(Role role);
}
