/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Warriors;

import java.util.ArrayList;

/**
 *
 * @author eboni
 */
public class WarriorBuilder {
    
    public static Warrior build(String name, ElementalType type, int vida, String warriorImage, ArrayList weapons){
        
        Warrior newWarrior = new Warrior(name, type, vida, warriorImage);
        newWarrior.setWeapons(weapons);//Pone el conjunto de armas al luchador.
        
        return newWarrior;
        
        
    }
    
    
}
