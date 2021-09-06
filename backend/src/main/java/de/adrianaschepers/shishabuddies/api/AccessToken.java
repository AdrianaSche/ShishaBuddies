package de.adrianaschepers.shishabuddies.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessToken {

     //token for browser should be returned as an object
    private String token;

}
