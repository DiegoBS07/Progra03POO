/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comands;

import java.util.Locale;

/**
 *
 * @author Andrés
 */
public class CommandDisplayFactory {
    
    public static CommandDisplay getCommandDisplay(String[] args){
        String displayType = args[1].toUpperCase();
        switch (displayType){
            //TODO: Colocar nuevos comandos
            default:
                return null;
        }
    }
}
