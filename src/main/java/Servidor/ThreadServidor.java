/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Comands.Command;
import Comands.CommandGiveup;
import Comands.CommandSkipTurn;
import Comands.*;
import Warriors.Warrior;
import Warriors.WarriorRegister;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ThreadServidor extends Thread{
    private Server server;
    private Socket socket;
    //Streams para leer y escribir objetos
    public ObjectInputStream objectListener;
    public ObjectOutputStream objectSender;
    public String name;
    private boolean turn;
    private boolean isReady = false;
    public boolean isActive = true;
    public boolean isRunning = true;
    private WarriorRegister Warriors = new WarriorRegister();
    
    
    
    

    public ThreadServidor(Server server, Socket socket) {
        try {
        this.server = server;
        this.socket = socket;
        objectSender =  new ObjectOutputStream (socket.getOutputStream());
        objectSender.flush();
        objectListener =  new ObjectInputStream (socket.getInputStream());
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void run (){
        Command comando;
        this.respondAction(new CommandWelcome());
        while (isRunning){
            try {
                comando = (Command)objectListener.readObject();
                server.refFrame.writeMessage("ThreadServer recibió: " + comando);
                //Todo: Bitacora

                comando.processForServer(this);

                if (comando instanceof CommandSkipTurn){
                    server.executeCommand(comando, this);
                }
//                if (comando instanceof CommandSpecialAttacks && ((CommandSpecialAttacks)comando).isValid()){
//                    server.executeCommand(comando, this);
//                }

                if (isActive && !comando.isManagedFromServer())
                    server.executeCommand(comando, this);
                if (comando instanceof CommandGiveup){
                    server.executeCommand(comando, this);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }  
        } 
    }
    
    public void showAllClients (){
        this.server.showAllNames();
    }
    
    
    public boolean isValidName(String name){
        if (name.isEmpty()){
            return false;
        }else{
            return !this.server.isExistentName(name);
        }
    }
    
    
    public void throwError(IError error){
        try {
            objectSender.writeObject(error);
            objectSender.flush();
        } catch (IOException ex) {
           
        }
    }
    
    public void respondAction(Command command){
        try {
            objectSender.writeObject(command);
            objectSender.flush();
        } catch (IOException ex) {
       
        }
    }

//    public boolean isExistentFighter (String name){
//        for (Fighter fighter : fighters.getFighters()){
//            if (fighter.getName().equalsIgnoreCase(name)){
//                return true;
//            }
//        }
//        return false;
//    }

//    public Fighter ObtainExistentFighter (String name){
//        for (Fighter fighter : fighters.getFighters()){
//            if (fighter.getName().equalsIgnoreCase(name)){
//                return fighter;
//            }
//        }
//        return null;
//    }

    public ObjectInputStream obtainSpecificListener(String name){
        return this.server.obtainListener(name);
    }
    
    public void writeInFrame(String message){
        this.server.getRefFrame().writeMessage(message);
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public WarriorRegister getWarriors() {
        return Warriors;
    }

    public void setWarriors(WarriorRegister Warriors) {
        this.Warriors = Warriors;
    }
    
    
}
