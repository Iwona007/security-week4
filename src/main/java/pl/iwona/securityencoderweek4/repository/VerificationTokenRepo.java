package pl.iwona.securityencoderweek4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.iwona.securityencoderweek4.model.VerificationToken;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByValue(String value);
    VerificationToken findByApiUser(String username);
}
