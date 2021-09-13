package de.adrianaschepers.shishabuddies.api;

import lombok.*;

//send from Browser to Backend so that server can generate Token
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credentials {

    private String userName;
    private String password;
}
