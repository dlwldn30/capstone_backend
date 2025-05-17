package capstone.backend.auth.controller;

import capstone.backend.auth.dto.LoginRequestDto;
import capstone.backend.auth.dto.TokenResponseDto;
import capstone.backend.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        String token = authService.authenticateAndGenerateToken(requestDto);
        return ResponseEntity.ok(new TokenResponseDto(token));
    }
}
