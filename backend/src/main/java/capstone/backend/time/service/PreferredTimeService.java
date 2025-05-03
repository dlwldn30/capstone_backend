package capstone.backend.time.service;

import capstone.backend.time.domain.PreferredTime;
import capstone.backend.time.repository.PreferredTimeRepository;
import capstone.backend.user.domain.User;
import capstone.backend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Service
@RequiredArgsConstructor
public class PreferredTimeService {

    private final UserRepository userRepository;
    private final PreferredTimeRepository preferredTimeRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initPreferredTime() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            // 이미 preferred_time에 해당 유저가 있으면 생략
            boolean exists = preferredTimeRepository.existsByUser(user);
            if (!exists && user.getPreferredTime() != null) {
                PreferredTime time = PreferredTime.builder()
                        .user(user)
                        .timeSlot(user.getPreferredTime().name())
                        .build();
                preferredTimeRepository.save(time);
                System.out.println("✅ 저장됨: " + time.getTimeSlot() + " - " + user.getUsername());
            }
        }

        System.out.println("✅ ApplicationReadyEvent = PreferredTimeService 실행 완료");
    }
}
