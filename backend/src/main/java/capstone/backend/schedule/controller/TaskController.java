package capstone.backend.schedule.controller;

import capstone.backend.schedule.service.TaskService;
import capstone.backend.schedule.domain.Task;
import capstone.backend.user.domain.User;
import capstone.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserRepository userRepository;

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task,
                             @AuthenticationPrincipal UserDetails userDetails) {

        // Spring Security 세션에 저장된 email 기반으로 실제 User 엔티티 조회
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("유저 정보를 찾을 수 없습니다."));

        task.setUser(user); // 🔥 연관관계 설정
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping
    public String listTasks(Model model,
                            @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("유저 정보를 찾을 수 없습니다."));

        model.addAttribute("tasks", taskService.getTasksByUser(user));
        return "task-list";
    }
}



