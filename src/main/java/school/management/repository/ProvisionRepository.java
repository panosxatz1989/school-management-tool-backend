package school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.management.entity.Provision;

@Repository
public interface ProvisionRepository extends JpaRepository<Provision, Long> {
}
