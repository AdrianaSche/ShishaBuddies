package de.adrianaschepers.shishabuddies.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "setup_analysis")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyzeSetupEntity {

    @Id
    @GeneratedValue
    @Column(name= "id")
    private Long id;

    @Column(name="smoking_duration")
    private String smokingDuration;

    @Column(name="number_of_heads")
    private Long numOfSmokedHeads;

    @Column(name="comment",columnDefinition="TEXT")
    private String comment;
}
