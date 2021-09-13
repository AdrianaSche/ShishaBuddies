
package de.adrianaschepers.shishabuddies.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "settings")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingsEntity {

//erweiterte Profileintellungen
    @GeneratedValue
    @Id
    @Column(name = "id",nullable = false)
    private Long id;

    //eine Settings-Entity gehört zu einer userEntity
    @OneToOne(mappedBy = "settings")
    private UserEntity user;

    @Column(name = "number_of_hookahs")
    private Long numOfHookahs;

    @Column(name = "number_of_hookah_heads")
    private Long numOfHookahHeads;

    @Column(name = "number_of_tobaccos")
    private Long numOfTobaccos;

    @Column(name = "fav_hookah")
    private String favHookah;

    @Column(name = "fav_hookah_head")
    private String favHookahHead;

    @Column(name = "fav_tobacco")
    private String favTobacco;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingsEntity that = (SettingsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(userEntity, that.userEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userEntity);
    }*/
}

