package capstone.backend.user.domain;

import capstone.backend.time.domain.PreferredTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id // 기본 키(primary key) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment 전략
    private Long id;

    @Column(nullable = false, unique = true) // 중복 불가, 필수값
    private String username; // 사용자 이름 또는 ID

    private String password; // 비밀번호 (※ 추후 암호화 필요)

    private String email; // 이메일 주소


    // 선호 시간대 입력
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreferredTime> preferredTimes = new  ArrayList<>();
}
