package tracker.covid.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Covid {
    private Long id;
    private String combinedKey;
    private Long active;
    private Long recovered;
}
