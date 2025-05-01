package capstone.backend.user.repository;

import capstone.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 필요시 커스텀 메서드 추가 가능
}
