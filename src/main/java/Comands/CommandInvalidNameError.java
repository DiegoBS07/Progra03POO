/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comands;

import Cliente.Client;

/**
 *
 * @author Andrés
 */
public class CommandInvalidNameError extends CommandGeneralError {
    
    public CommandInvalidNameError(String errorMsg) {
        super(errorMsg);
    }
    
        @Override
        public void processInClient(Client client){
        client.getRefFrame().writeMessage(this.getErrorMsg());
        client.getRefFrame().showNameError();
    }
    
}
