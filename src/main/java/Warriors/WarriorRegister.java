/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Warriors;

import java.util.ArrayList;

/**
 *
 * @author eboni
 */
public class WarriorRegister {
        private static final int MAXWARRIORS = 4;
    private ArrayList<Warrior> Warriors = new ArrayList<Warrior>();
    
    public void registerWarrior(Warrior newWarrior){
        if(!limitReached()){
            this.Warriors.add(newWarrior);   
        }


    }

    public Warrior getWarrior(int index){
        return Warriors.get(index);
    }

    public boolean limitReached(){
        return this.Warriors.size() >= MAXWARRIORS; //Devuelve un boolean indicando si se ha alcanzado el limite o no.
    }

    public ArrayList<Warrior> getWarriors() {
        return Warriors;
    }

    public void setWarriors(ArrayList<Warrior> Warriors) {
        this.Warriors = Warriors;
    }

    
}
