//package capstone.backend.schedule.service;
//
//import capstone.backend.schedule.dto.TaskRequestDTO;
//import capstone.backend.schedule.repository.TaskRepository;
//import capstone.backend.user.repository.UserRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//
//@Service
//@RequiredArgsConstructor
//public class AutoScheduleService {
//
//    private final TaskRepository taskRepository;
//    private final UserRepository userRepository;
//    private final ObjectMapper objectMapper;
//
//    @Scheduled(fixedDelay = 3600000) // ✅ 매 시간마다 실행 (원하면 수동 호출로 변경 가능)
//    public void importTasksFromJson() {
//        try {
//            File file = new File("src/main/resources/generated_schedule.json");
//            if (!file.exists()) {
//                System.out.println("⚠️ 일정 JSON 파일이 존재하지 않습니다.");
//                return;
//            }
//
//            List<TaskRequestDTO> tasks = objectMapper.readValue(
//                    file, new TypeReference<>() {}
//            );
//
//            // 임시로 userId=1 고정 (실제 앱에서는 로그인 사용자 or 매핑 사용자 지정 필요)
//            User user = userRepository.findById(1L).orElseThrow();
//
//            for (TaskRequestDTO dto : tasks) {
//                Task task = Task.builder()
//                        .title(dto.getTitle())
//                        .description(dto.getDescription())
//                        .category(dto.getCategory())
//                        .isRepeated(dto.isRepeatEvent())
//                        .completed(dto.isCompleted())
//                        .priority(dto.getPriority())
//                        .preference(dto.getPreference())
//                        .startTime(dto.getStartTime())
//                        .endTime(dto.getEndTime())
//                        .user(user)
//                        .build();
//
//                taskRepository.save(task);
//            }
//
//            System.out.println("✅ 자동 스케줄 저장 완료!");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}