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
    private String warriorImage;

    public Warrior(String name, ElementalType type, int vida, String warriorImage) {
        this.name = name;
        this.type = type;
        this.vida = 100; //Vida inicial del guerrero.
        this.warriorImage = warriorImage;
    }
    
    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public String getName() {
        return name;
    }

    public ElementalType getType() {
        return type;
    }

    public String getWarriorImage() {
        return warriorImage;
    }
    
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    
    
    
}
