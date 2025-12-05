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
    KRAKEN_BREATH(7),  //attack Andres kraken_breath x 20 y 20
    THREE_LINES(15), //attack Andres three_lines x 20 y 20 x 10 y 20 x 2 y 20
    THUNDER_RAIN(3), //attack Andres thunder_rain
    SHOAL(3), //attack Andres shoal
    PULP(3),    //attack Andres pulp
    EEL_ATTACK(3),
    SHARK_ATTACK(3),
    TENTACLES(15),
    RELEASE_KRAKEN(7),
    POSEIDON_THUNDERS(3),
    CONTROL_KRAKEN(3),
    VOLCANO_RISING(7),
    SWIRL_RISING(7),
    RADIOACTIVE_RUSH(3),
    VOLCANO_EXPLOSION(7),
    THERMAL_RUSH(7),
    SEND_HUMAN_GARBAGE(7),
    THREE_NUMBERS(6),
    RESPOND_THREE_NUMBERS(4),
    MESSAGE (2), //message hola a todos
    PRIVATE_MESSAGE(3), //private Andres hola andres
    GIVEUP (1), //giveup
    NAME (2),
    ERROR(1),
    WELCOME(1),
    CREATE_FIGHTER(8),
    READY(1),
    DISPLAY(2),
    DEATH(1),
    HEALING(4),
    RESISTANCE(4),
    SKIP_TURN(1),
    LOG(1),
    SUMMARY(1),
    CHECK_CELL(5),
    ENEMY_STATE(2),
    POWER(4);

    //.. AGREGARÍAN MÁS TIPOS DE COMANDO
    
    
    private int requiredParameters;

    private CommandType(int requiredParameters) {
        this.requiredParameters = requiredParameters;
    }

    public int getRequiredParameters() {
        return requiredParameters;
    }
    
    
    
    
    
}
