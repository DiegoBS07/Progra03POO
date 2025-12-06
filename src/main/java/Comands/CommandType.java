/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Comands;

/**
 *
 * @author diego
 */
public enum CommandType {
    MESSAGE (2), //message hola a todos
    PRIVATE_MESSAGE(3), //private Andres hola andres
    GIVEUP (1), //giveup
    NAME (2),
    ERROR(1),
    WELCOME(1),
    CREATE_WARRIOR(9), // Create_warrior Warrior tipo 1 2 3 4 5 -> Los numeros son las armas a crear.
    READY(1),
    DISPLAY(2),
    DEATH(1),
    SKIP_TURN(1),
    ENEMY_STATE(2);

    //.. AGREGARÍAN MÁS TIPOS DE COMANDO
    
    
    private int requiredParameters;

    private CommandType(int requiredParameters) {
        this.requiredParameters = requiredParameters;
    }

    public int getRequiredParameters() {
        return requiredParameters;
    }
    
    
    
    
    
}
