package de.adrianaschepers.shishabuddies.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Settings {

   private Long numberOfHookahs;
   private Long numberOfHookahHeads;
   private Long numberOfTobaccos;
   private String  favHookah;
   private String favHookahHead;
   private String favTobacco;
   private Long smokingDurationInMinutes=0L;

}
