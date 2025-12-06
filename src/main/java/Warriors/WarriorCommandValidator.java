/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Warriors;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eboni
 */
public class WarriorCommandValidator {

//    public static boolean isWeaponUnique(ArrayList<Warrior> Warriors, Fighter newFighter){
//        for(Fighter f : Fighters){
//            if(f.getHealingPercent() == newFighter.getHealingPercent() || f.getPowerPercent()== newFighter.getPowerPercent() || f.getHealingPercent() == newFighter.getHealingPercent()){
//                return false;//Revisa si alguno es igual al ya existente.
//        }
//    }
//        return true;
//      
//}

    public static boolean isNameUnique(ArrayList<Warrior> Warriors, Warrior neWarrior){ //Revisa que los warriors tengan nombres unicos
        for(Warrior w : Warriors){
            if(w.getName().equalsIgnoreCase(neWarrior.getName())){
                return false; //Si ese nombre ya existe tira falso.
            }
        }
        return true;
    }
    
   
}

