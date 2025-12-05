/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comands;

import Cliente.Client;
import Servidor.ThreadServidor;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public abstract class CommandAttack extends Command{
    private String senderName;
    public CommandAttack(CommandType commandType, String[] args) { //ATTACK Andres tipo
        super(commandType, args);
    }

    public abstract void processForServer(ThreadServidor threadServidor);

    public boolean isExistentCoordinate(ArrayList<Point> cellsCoordinates, int x, int y){
        if (cellsCoordinates.isEmpty()){
            return false;
        }
        for (Point point : cellsCoordinates){
            if (point.equals(new Point(x, y))){
                return true;
            }
        }
        return false;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
