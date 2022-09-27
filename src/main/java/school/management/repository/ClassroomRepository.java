package school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.management.entity.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Short> {
}
