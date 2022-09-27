package school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.management.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
