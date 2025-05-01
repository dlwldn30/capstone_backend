package capstone.backend.schedule.repository;

import capstone.backend.schedule.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
