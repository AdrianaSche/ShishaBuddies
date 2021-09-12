/*
package de.adrianaschepers.shishabuddies.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_settings")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SettingsEntity {

    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private Long id;

    //eine Settings-Entity gehört zu einer userEntity
    @OneToOne(mappedBy = "user_settings")
    private UserEntity userEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingsEntity that = (SettingsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(userEntity, that.userEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userEntity);
    }
}
*/
