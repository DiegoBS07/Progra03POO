package Comands;

import Servidor.ThreadServidor;

public class CommandSkipTurn extends Command {
    public CommandSkipTurn(String[] parameters) {
        super(CommandType.SKIP_TURN, parameters);
    }

    @Override
    public void processForServer(ThreadServidor threadServidor) {
        this.setManagedFromServer(true);
    }
}
