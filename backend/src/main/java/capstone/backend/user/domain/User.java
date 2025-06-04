package capstone.backend.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private PreferredTime preferredTime;

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return Collections.emptyList(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }


     // 경험치 및 랭크 관련 필드
    private int experience = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_rank")
    private Rank userRank = Rank.BRONZE;

    public enum Rank {
        BRONZE, SILVER, GOLD, DIAMOND
    }

    // ✅ 경험치 추가 메서드 (성공 횟수에 따라 경험치 증가량 달라짐)
    public void addExperience(int successCount) {
        int expGained = calculateExp(successCount);
        this.experience += expGained;
        updateRank();
        System.out.println("✅ 성공 " + successCount + "회 → 경험치 " + expGained + " 증가 (총: " + experience + ")");
    }

    // 경험치 증가량 계산 로직 (예: 성공횟수에 따라 가중치)
    // 하루에 성공한 횟수 입력
    private int calculateExp(int successCount) {
        if (successCount <= 0) return 0;
        else if (successCount == 1) return 5;
        else if (successCount <= 3) return 10;
        else if (successCount <= 5) return 15;
        else return 20 + (successCount - 5) * 2; // 6회 이상은 추가 보너스
    }

    // ✅ 랭크 갱신
    public void updateRank() {
        if (experience >= 500) userRank = Rank.DIAMOND;
        else if (experience >= 300) userRank = Rank.GOLD;
        else if (experience >= 100) userRank = Rank.SILVER;
        else userRank = Rank.BRONZE;
    }


}
