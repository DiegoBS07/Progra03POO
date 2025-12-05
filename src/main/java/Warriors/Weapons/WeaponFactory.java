/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Warriors.Weapons;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.*;
import java.util.Random;

/**
 *
 * @author eboni
 */
public class WeaponFactory {
        private Random random = new Random();

        
    public List<Weapon> createWeapons(List<String> names) { //Crea exactamente 5 armas y cada arma tiene un porcentaje de daño randomizado para cada guerrero.
        List<Weapon> weapons = new ArrayList<>();

        for (int i = 0; i < 5 && i < names.size(); i++) {
            String name = names.get(i);

            // Daño randomizado de 20 a 100 por tipo
            Map<Type, Integer> damageByType = new EnumMap<>(Type.class); //Genera un daño aparte para cada uno de los tipos.
            for (Type type : Type.values()) {
                damageByType.put(type, 20 + random.nextInt(81));
            }

            weapons.add(new Weapon(name, damageByType));
        }

        return weapons;
    }
}

    
