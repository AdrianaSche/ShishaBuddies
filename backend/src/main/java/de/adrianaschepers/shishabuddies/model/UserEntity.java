package de.adrianaschepers.shishabuddies.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "shishaUser")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "username",nullable = false)
    private String userName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "role")
    private String role;
}
