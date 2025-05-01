package capstone.backend.schedule.service;

import capstone.backend.schedule.domain.Task;
import capstone.backend.schedule.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }
}
