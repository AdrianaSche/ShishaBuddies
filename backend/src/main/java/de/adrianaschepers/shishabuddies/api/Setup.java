package de.adrianaschepers.shishabuddies.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Setup {
    private String title;
    private String hookah;
    private String hookahHead;
    private String tobacco;
    private String carbon;
    private String carbonTop;
    private String accessories;
    //private Duration smokingDuration;
    private Long smokingDuration;
    private Long numOfHeads;
    private String comment;
    private Long setupCount;

}
