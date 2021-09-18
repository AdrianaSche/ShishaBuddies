
package de.adrianaschepers.shishabuddies.model;

import lombok.*;

import javax.persistence.*;


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

   /* @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity userEntity;*/

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

    @Column(name= "comment")
    private String comment;

}

