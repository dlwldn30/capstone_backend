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


    //랭크
    private int experience = 0;

    @Enumerated(EnumType.STRING)
    private Rank rank = Rank.BRONZE;


    public enum Rank {
        BRONZE, SILVER, GOLD, DIAMOND
    }
    // ✅ 경험치 추가 및 랭크 갱신 메서드
    public void addExperience(int amount) {
        this.experience += amount;
        updateRank();
    }

    public void updateRank() {
        if (experience >= 500) rank = Rank.DIAMOND;
        else if (experience >= 300) rank = Rank.GOLD;
        else if (experience >= 150) rank = Rank.SILVER;
        else rank = Rank.BRONZE;
    }
}
