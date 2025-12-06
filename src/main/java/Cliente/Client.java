/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import Comands.Command;
import Comands.CommandFactory;
import Warriors.Warrior;
import Warriors.WarriorRegister;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class Client {
    private final int PORT = 35500;
    private final String IP_ADDRESS = "localhost";
    private Socket socket;
    private FrameClient refFrame;
    public ObjectInputStream objectListener;
    public ObjectOutputStream objectSender;
    private ThreadClient threadClient;
    private boolean isReady = false;
    private boolean dead = false;
    
    public String name;
    private WarriorRegister Warriors = new WarriorRegister(); //Lista que tendra cada cliente con sus 4 guerreros

    public Client(FrameClient refFrame, String name) {
        this.refFrame = refFrame;
        this.name = name;
        this.connect();
    }
    
    public Client (FrameClient refFrame){
        this.refFrame = refFrame;
    }
    
    private void connect (){
        try {
            socket = new Socket(IP_ADDRESS , PORT);
            objectSender =  new ObjectOutputStream (socket.getOutputStream());
            objectSender.flush();
            objectListener =  new ObjectInputStream (socket.getInputStream());
            
            threadClient =  new ThreadClient(this);
            threadClient.start();
            
            String[] args = {"NAME", this.name};
            objectSender.writeObject(CommandFactory.getCommand(args));
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sendName(){
        String[] args = {"NAME", this.name};
        try {
            objectSender.writeObject(CommandFactory.getCommand(args));
        } catch (IOException ex) {
            System.getLogger(Client.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void respondAction(Command command){
        try {
            objectSender.writeObject(command);
            objectSender.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ObjectInputStream getObjectListener() {
        return objectListener; 
    }

    public ObjectOutputStream getObjectSender() {
        return objectSender;
    }
    
    

    public FrameClient getRefFrame() {
        return refFrame;
    }

    public boolean isIsReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }

    public WarriorRegister getWarriors() {
        return Warriors;
    }

    public void setWarriors(WarriorRegister Warriors) {
        this.Warriors = Warriors;
    }
    
    
}



