package tracker.covid.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Covid {
    private String combinedKey; // 11 country -> in cvs called combinedKey
    private Long active; // 10
    private Long recovered; // 9
    private Long confirmed; // 7
    private Long death; // 8
    private LocalDateTime lastUpdate; // 4
}
