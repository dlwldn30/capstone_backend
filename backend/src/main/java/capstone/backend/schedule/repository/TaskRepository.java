package capstone.backend.schedule.repository;

import capstone.backend.schedule.domain.Task;
import capstone.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user); // 로그인한 사용자 기준으로 조회
}
