package school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.management.entity.ProvisionType;

@Repository
public interface ProvisionTypeRepository extends JpaRepository<ProvisionType, Integer> {
}
