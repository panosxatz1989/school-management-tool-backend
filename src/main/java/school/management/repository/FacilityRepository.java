package school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.management.entity.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {
}
