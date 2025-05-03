package capstone.backend.time.repository;

import capstone.backend.time.domain.PreferredTime;
import capstone.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferredTimeRepository extends JpaRepository<PreferredTime, Long> {
    boolean existsByUser(User user);

}
