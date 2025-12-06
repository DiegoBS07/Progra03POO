/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Warriors;

/**
 *
 * @author eboni
 */
public class WarriorImageManager {
    
    public static String imageFileGetter(String imageIndicator) {
        switch (imageIndicator.toLowerCase()) {
            case "acid":
                return "/WarriorImages/Acid.png";
            case "darkmagic":
                return "/WarriorImages/DarkMagic.png";
            case "fire":
                return "/WarriorImages/Fire.png";
            case "ghost":
                return "/WarriorImages/Ghost.png";
            case "holymagic":
                return "/WarriorImages/HolyMagic.png";
            case "ice":
                return "/WarriorImages/Ice.png";
            case "iron":
                return "/WarriorImages/Iron.png";
            case "thunder":
                return "/WarriorImages/Thunder.png";
            case "water":
                return "/WarriorImages/Water.png";
            case "wind":
                return "/WarriorImages/Wind.png";
            case "kraken":
                return "/WarriorImages/Kraken.jpg";
            case "poseidon":
                return "/WarriorImages/Poseidon.jpg";
            case "fish":
                return "/WarriorImages/Fish.png";
            case "volcano":
                return "/WarriorImages/Volcano.jpg";
            default:
                return "/WarriorImages/default.png";
        }
    }

  }

