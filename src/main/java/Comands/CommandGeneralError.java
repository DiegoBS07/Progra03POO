/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comands;

import Cliente.Client;
import Servidor.ThreadServidor;

/**
 *
 * @author Andrés
 */
public class CommandGeneralError extends Command implements IError{
    private String errorMsg;
    private static final String[] args = {"ERROR"};

    public CommandGeneralError(String errorMsg) {
        super(CommandType.ERROR, args);
        this.errorMsg = errorMsg;
        this.declareError();
    }
    
    
    @Override
    public void processForServer(ThreadServidor threadServidor) {
        threadServidor.throwError(this);
    }
    
    @Override
        public void processInClient(Client client){
        client.getRefFrame().writeMessage(this.errorMsg);
        
    }
    

    @Override
    public void declareError() {
        this.setManagedFromServer(IError.ERROR_STATUS);
    }

    public String getErrorMsg() {
        return errorMsg;
    }
    
    
}
