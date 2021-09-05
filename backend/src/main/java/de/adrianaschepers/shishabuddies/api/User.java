package de.adrianaschepers.shishabuddies.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private String name;
    private String password;
}
