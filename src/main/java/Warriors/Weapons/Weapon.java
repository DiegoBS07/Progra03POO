/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Warriors.Weapons;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Map;

/**
 *
 * @author eboni
 */
public class Weapon {
    private String name;//Nombre del arma.
    private Map<Type, Integer> damageByType; //Genera un arreglo que contiene el daño por tipo generado por la Factory (Donde se accede haciendo uso del tipo).
    private boolean available; //Indica si el arma esta disponible para uso.

    public Weapon(String name, Map<Type, Integer> damageByType) {
        this.name = name;
        this.damageByType = damageByType;
        this.available = true;
    }

    public int calculateDamage(Type warriorType) {
        return damageByType.getOrDefault(warriorType, 20); 
    }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return name + " " + damageByType.toString();
    }
}
