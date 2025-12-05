/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Warriors;

import Warriors.Weapons.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eboni
 */
public class Warrior {
    private String name; //Nombre del guerrero.
    private ElementalType type;
    private List<Weapon> weapons = new ArrayList<>(); //Armas por guerreros.
    private int vida;

    public Warrior(String name, ElementalType type, int vida) {
        this.name = name;
        this.type = type;
        this.vida = vida;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }
    
    
    
}
