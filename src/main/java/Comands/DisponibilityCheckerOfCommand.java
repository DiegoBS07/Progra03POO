/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comands;


import java.util.ArrayList;

/**
 *
 * @author Andrés
 */
public class DisponibilityCheckerOfCommand {
    
//    public static boolean checkInicialCommandDisponibilty(Command command, boolean isClientReady, ArrayList<Fighter> fighters){
//        if (!isClientReady){
//            return !(command instanceof CommandAttack 
//                    || command instanceof CommandGiveup
//                    || command instanceof CommandName
//                    || command instanceof CommandDisplay
//                    || command instanceof CommandSkipTurn);
//        }
//    }
//        Client is ready
//        if ((command instanceof CommandReady
//                || command instanceof CommandName)){
//            return false;
//        }else if (command instanceof CommandAttack){
//            if (command instanceof ISpecialCommandAttack){
//                return true;
//            }
//            for (Fighter fighter : fighters){
//                for (IAttack typeAttack: fighter.getAttackGroup().getAttacks()){
//                    Class<?>[] interfaces = typeAttack.getClass().getInterfaces();
//                    for (Class<?> iface : interfaces) {
//                        if (iface.isInstance(command)) {
//                            return true;
//                        }
//                    }
//
//                }
//            }
//            return false;
//        }else{
//            return true;
//        }
//    }

    public static boolean checkTurnCommandDisponibility(Command command){
        if (command instanceof CommandAttack || command instanceof CommandGiveup || command instanceof CommandSkipTurn){
            return false;
        }
        return true;
    }

    public static boolean checkDeadCommandDisponibility(Command command){
        if (command instanceof CommandMessage || command instanceof CommandPrivateMessage
                || command instanceof CommandDisplay
                || command instanceof CommandGiveup){
            return true;
        }
        return false;
    }
}
