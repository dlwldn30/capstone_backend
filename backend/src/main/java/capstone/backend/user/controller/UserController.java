package capstone.backend.user.controller;

import capstone.backend.user.domain.User;
import capstone.backend.user.dto.UserSignupRequestDto;
import capstone.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/users/new")
    public String userForm() {
        return "user-form"; // templates/user-form.html
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // templates/login.html
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDto requestDto) {
        System.out.println("회원가입 요청 도착: " + requestDto.getEmail());
        userService.signup(requestDto);  // 서비스에 DTO 전달
        return ResponseEntity.ok("회원가입 성공");
    }
}
