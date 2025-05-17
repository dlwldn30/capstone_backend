package capstone.backend.user.service;

import capstone.backend.user.domain.User;
import capstone.backend.user.dto.UserSignupRequestDto;
import capstone.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void save(User user) {
        userRepository.save(user);
    }

    public void signup(UserSignupRequestDto dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .username(dto.getUsername())
                .build();

        System.out.println("🔔 UserService.signup() 실행됨!");


        userRepository.save(user);
    }


}

