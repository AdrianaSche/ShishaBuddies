
package de.adrianaschepers.shishabuddies.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "setup")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetupEntity {

    @Id
    @GeneratedValue
    @Column(name= "id", nullable = false)
    private Long id;

    // 1:1 zu analyzeSetup
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn (name = "analyzer_id",referencedColumnName = "id")
    private AnalyzeSetupEntity analyzeSetupEntity;

   /* @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity userEntity;*/


    @Column(name="date")
    private Date date;

    @Column(name = "hookah")
    private String hookah;

    @Column(name = "hookah_head")
    private String hookahHead;

    @Column(name = "tobacco")
    private String tobacco;

    @Column(name= "carbon")
    private String carbon;

    @Column(name = "carbonTop")
    private String carbonTop;

    @Column(name= "accessoires")
    private String accessories;

    @Column (name= "picture_of_setup")
    private String avatar;

}

