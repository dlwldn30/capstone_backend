package capstone.backend.user.controller;

import capstone.backend.user.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/experience")
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping("/reward")
    public ResponseEntity<String> reward(
            @RequestParam Long userId,
            @RequestParam int successCount) {
        experienceService.rewardExperience(userId, successCount);
        return ResponseEntity.ok("경험치 추가 완료");
    }
}
