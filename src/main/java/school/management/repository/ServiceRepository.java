package school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.management.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
