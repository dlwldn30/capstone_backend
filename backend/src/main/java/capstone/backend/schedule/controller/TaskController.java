package capstone.backend.schedule.controller;

import capstone.backend.schedule.dto.TaskRequestDTO;
import capstone.backend.schedule.service.TaskService;
import capstone.backend.schedule.domain.Task;
import capstone.backend.user.CustomUserDetails;
import capstone.backend.user.domain.User;
import capstone.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserRepository userRepository;

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

//    @PostMapping
//    public Task createTask(@ModelAttribute Task task,
//                             @AuthenticationPrincipal UserDetails userDetails) {
//
//        // Spring Security 세션에 저장된 email 기반으로 실제 User 엔티티 조회
//        User user = userRepository.findByEmail(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("유저 정보를 찾을 수 없습니다."));
//
//        task.setUser(user); // 🔥 연관관계 설정
//        taskService.save(task);
//        return taskService.save(task);
//    }

    @GetMapping
    public String listTasks(Model model,
                            @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("유저 정보를 찾을 수 없습니다."));

        model.addAttribute("tasks", taskService.getTasksByUser(user));
        return "task-list";
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskRequestDTO requestDto,
                                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            System.out.println("❌ userDetails가 null입니다. JWT 인증이 실패했을 수 있습니다.");
            return ResponseEntity.status(401).body("인증 실패");
        }

        System.out.println("✅ TaskController: POST 요청 들어옴");
        taskService.createTask(requestDto, userDetails.getUser());
        return ResponseEntity.ok("Task 저장 완료");
    }
}



