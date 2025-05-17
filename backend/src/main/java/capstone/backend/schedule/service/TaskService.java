package capstone.backend.schedule.service;

import capstone.backend.schedule.domain.Task;
import capstone.backend.schedule.dto.TaskRequestDTO;
import capstone.backend.schedule.repository.TaskRepository;
import capstone.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void createTask(TaskRequestDTO dto, User user) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .isRepeated(dto.isRepeatEvent())
                .completed(dto.isCompleted())
                .priority(dto.getPriority())
                .preference(dto.getPreference())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .user(user)
                .build();
        taskRepository.save(task);
    }
    public List<Task> getTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }
}
