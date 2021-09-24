package de.adrianaschepers.shishabuddies.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


@Entity
@Table(name = "shisha_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity  {

    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private Long id;

    //user-setting: 1:1, funktioniert nicht mehr.
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn (name = "settings_id")
    private SettingsEntity settings;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "userEntity",fetch = FetchType.EAGER)
    private List<SetupEntity> setups;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "username",nullable = false)
    private String userName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    public void addSetup(SetupEntity setupEntity){
        setups.add(setupEntity);
    }

    public SetupEntity findSetupByTitle(String title){
        for (SetupEntity setupEntity: setups) {
            if(setupEntity.getTitle().equals(title)){
                return setupEntity;
            }
        }
        throw new EntityNotFoundException(String.format("no setup with title = %s available!",title));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserEntity that = (UserEntity) o;
        return this.getUserName() != null && this.getUserName().equals(that.getUserName());
    }


    @Override
    public int hashCode() {
        return getUserName().hashCode();
    }


}
