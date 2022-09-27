package school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.management.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
