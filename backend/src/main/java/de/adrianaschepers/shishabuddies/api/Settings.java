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

   private int numberOfHookahs;
   private int numberOfHookahHeads;
   private int numberOfTobaccos;
   private String  favHookah;
   private String favHookahHead;
   private String favTobacco;

}
