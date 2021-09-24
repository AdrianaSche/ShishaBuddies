
package de.adrianaschepers.shishabuddies.model;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "setup")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SetupEntity {

    @Id
    @GeneratedValue
    @Column(name= "id", nullable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userEntity")
    private UserEntity userEntity;

    @Column(name = "title")
    private String title;

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

    @Column(name="smoking_duration")
    private Long smokingDuration;


   /* @Column(name="number_of_heads")
    private Long numOfSmokedHeads;*/

    @Column(name="comment",columnDefinition="TEXT")
    private String comment;

    @Column(name = "setup_count")
    private Long setupCount;


    @Column (name= "picture_of_setup")
    private String avatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetupEntity that = (SetupEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }


}

