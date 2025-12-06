/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Warriors;

/**
 *
 * @author eboni
 */
public class GetSelectedType {

    public static ElementalType fromText(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }

        try {
            // Normalizamos: quitamos espacios y pasamos a mayúsculas
            return ElementalType.valueOf(text.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo no válido: " + text);
            return null;
        }
    }
}