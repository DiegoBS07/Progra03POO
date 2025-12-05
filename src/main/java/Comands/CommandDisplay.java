/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comands;

import Servidor.ThreadServidor;

/**
 *
 * @author Andrés
 */
public abstract class CommandDisplay extends Command{

    public CommandDisplay(String[] parameters) {
        super(CommandType.DISPLAY, parameters);
        this.setManagedFromServer(true);
    }
    
    
    
}
