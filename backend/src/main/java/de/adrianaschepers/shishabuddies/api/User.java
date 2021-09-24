package de.adrianaschepers.shishabuddies.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String lastName;
    private String firstName;
    private String email;
    private String userName;
    private String password;
    private Long smokingDurationInMinutes=0L;


}
