package capstone.backend.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDto {

    private String email;
    private String password;
    private String username;

    // 기본 생성자 필수 (스프링에서 JSON → 객체 매핑 시 필요)
    public UserSignupRequestDto() {}
}