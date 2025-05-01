package capstone.backend.schedule.controller;

import capstone.backend.schedule.service.TaskService;
import capstone.backend.schedule.domain.Task;
import capstone.backend.user.domain.User;
import capstone.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserRepository userRepository;

    @GetMapping("/new")
    public ModelAndView newTaskForm() {
        ModelAndView mav = new ModelAndView("task-form");
        mav.addObject("task", new Task());
        return mav;
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        task.setUser(user);
        taskService.save(task);
        return "redirect:/tasks/new";
    }
}

