package capstone.backend.user.repository;

import capstone.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u")
    List<User> findAllUsers();  // 전체 유저 강제 조회

    Optional<User> findByEmail(String email);  // ← 이거 추가!


}
