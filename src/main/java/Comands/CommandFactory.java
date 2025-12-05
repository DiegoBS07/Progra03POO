/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comands;

/**
 *
 * @author diego
 */
public class CommandFactory {
    
    
    public static Command getCommand(String[] args){
        String type = args[0].toUpperCase();
        
        switch (type) {
            case "ATTACK":
                return CommandAttackFactory.getCommandAttack(args);
            case "MESSAGE":
                return new CommandMessage(args);
            case "PRIVATE_MESSAGE":
                return new CommandPrivateMessage(args);
            case "GIVEUP":
                return new CommandGiveup(args);
            case "NAME":
                return new CommandName(args);
            case "READY":
                return new CommandReady(args);
            case "DISPLAY":
                return CommandDisplayFactory.getCommandDisplay(args);
            case "SKIP_TURN":
                return new CommandSkipTurn(args);
            default:    
                return null;
        }
        
        
    }
    
}
