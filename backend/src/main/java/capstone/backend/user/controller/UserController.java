package capstone.backend.user.controller;

import capstone.backend.time.domain.PreferredTime;
import capstone.backend.time.domain.TimePurpose;
import capstone.backend.time.domain.TimeSlot;
import capstone.backend.user.service.UserService;
import capstone.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/new")
    public String newUserForm() {
        return "user-form";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user, @RequestParam("timeSlot")TimeSlot timeSlot) {

        PreferredTime preferredTime = new PreferredTime(user, timeSlot, TimePurpose.FREE);
        user.getPreferredTimes().add(preferredTime);
        userService.save(user);
        return "redirect:/tasks/new";
    }
}


