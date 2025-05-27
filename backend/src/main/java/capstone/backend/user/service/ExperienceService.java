package capstone.backend.user.service;

import capstone.backend.user.domain.User;
import capstone.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final UserRepository userRepository;

    public void rewardExperience(Long userId, int amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        user.addExperience(amount); // 경험치 추가 + 랭크 갱신
        userRepository.save(user);
    }
}