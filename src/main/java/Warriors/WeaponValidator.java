/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Warriors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author eboni
 */
public class WeaponValidator {

    /**
     * Recibe 5 nombres de armas, valida que no se repitan y devuelve la lista.
     * Si hay error (menos de 5 nombres o duplicados), devuelve null.
     */
    public static List<String> validateAndCreate(List<String> names) {
        if (names == null || names.size() != 5) {
            return null; // error: cantidad incorrecta
        }

        Set<String> unique = new HashSet<>();
        List<String> validated = new ArrayList<>();

        for (String name : names) {
            String normalized = name.trim().toLowerCase();
            if (!unique.add(normalized)) {
                return null; // error: nombre repetido
            }
            validated.add(normalized);
        }

        return validated; // lista válida
    }
}
