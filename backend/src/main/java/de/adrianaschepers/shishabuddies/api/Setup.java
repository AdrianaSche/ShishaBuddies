package de.adrianaschepers.shishabuddies.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Setup {
    private String title;
    private String hookah;
    private String head;
    private String tobacco;
    private String carbon;
    private String carbonTop;
    private String accessories;
    private String smokingDuration;
    private Long numOfHeads;
    private String comment;
    private Long setupCount;

}
