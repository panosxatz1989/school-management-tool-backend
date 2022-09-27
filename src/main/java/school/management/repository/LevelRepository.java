package school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.management.entity.Level;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}
