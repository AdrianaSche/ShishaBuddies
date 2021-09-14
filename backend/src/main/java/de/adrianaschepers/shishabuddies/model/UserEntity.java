package de.adrianaschepers.shishabuddies.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "shisha_user") //warum geht das nicht, wenn die Table "user" heißt???
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

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn (name = "settings_id",referencedColumnName = "id")
    private SettingsEntity settings;

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
