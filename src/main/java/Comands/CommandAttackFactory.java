package Comands;

public class CommandAttackFactory {

    public static CommandAttack getCommandAttack (String[] args){
        String type = args[2].toUpperCase();
        //El tipo de ataque esta en la posicion 
        switch (type){
            //TODO: Colocar los nuevos ataques.
            default:
                return null;
        }
    }

}
