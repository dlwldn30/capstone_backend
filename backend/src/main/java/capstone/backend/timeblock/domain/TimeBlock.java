package capstone.backend.timeblock.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String dayOfWeek;

    @Column(nullable = false)
    private int startHour;

    @Column(nullable = false)
    private int endHour;

    @Column
    private String taskTitle;

}
