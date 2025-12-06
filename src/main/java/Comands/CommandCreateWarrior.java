/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comands;

import Servidor.ThreadServidor;
import Warriors.ElementalType;
import Warriors.GetSelectedType;
import Warriors.Warrior;
import Warriors.WarriorBuilder;
import Warriors.WarriorImageManager;
import Warriors.WeaponValidator;
import Warriors.Weapons.Weapon;
import Warriors.Weapons.WeaponFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eboni
 */
public class CommandCreateWarrior extends Command{

    public CommandCreateWarrior(CommandType type, String[] args) {
        super(CommandType.CREATE_WARRIOR, args);
        this.setManagedFromServer(true);
        return;
        
    }

    @Override
    public void processForServer(ThreadServidor threadServidor) {
                String[] args = this.getParameters();
        if(this.getParameters().length != 9){
            threadServidor.throwError(new CommandGeneralError("Cantidad de parámetros incorrecta"));
            return;
        }
        String nombre = null;
        String imagePath = null;
        ElementalType WarriorType = null;
        String Weapon1 = null;
        String Weapon2 = null;
        String Weapon3 = null;
        String Weapon4 = null;
        String Weapon5 = null;
        
        try {
            nombre = args[1];
            imagePath = WarriorImageManager.imageFileGetter(args[2]);
            WarriorType = GetSelectedType.fromText(args[3]);
            Weapon1 = args[4];
            Weapon2 = args[5];
            Weapon3 = args[6];
            Weapon4 = args[7];
            Weapon5 = args[8];
            
        } catch (NumberFormatException e) {
            threadServidor.throwError(new CommandGeneralError("Especificaciones de creación incorrectas"));
            return;
        }
            //Genera la lista de nombres
            List<String> weaponNames = new ArrayList<>();
                weaponNames.add(Weapon1);
                weaponNames.add(Weapon2);
                weaponNames.add(Weapon3);
                weaponNames.add(Weapon4);
                weaponNames.add(Weapon5);

        List<String> validatedNames = WeaponValidator.validateAndCreate(weaponNames); //Valida si los nombres de las armas son distintos y por lo tanto validos

        if (validatedNames == null) {
            threadServidor.throwError(new CommandGeneralError("Nombres de armas inválidos o repetidos"));
            return;
        }


        if(threadServidor.getWarriors().limitReached()){
          threadServidor.throwError(new CommandGeneralError("Cantidad máxima de luchadores alcanzada!"));
           return;

       }
        
       
        if(WarriorType == null){
        threadServidor.throwError(new CommandGeneralError("Tipo de Peleador Invalido!"));
        return;
            
        }
        
                // Crear armas con WeaponFactory
        WeaponFactory factory = new WeaponFactory();
        List<Weapon> weapons = factory.createWeapons(weaponNames);

       
        Warrior newWarrior = WarriorBuilder.build(nombre, WarriorType, 0, nombre, (ArrayList) weapons);

        
        threadServidor.getWarriors().registerWarrior(newWarrior);
        try {
            threadServidor.objectSender.writeObject(newWarrior);
        } catch (IOException ex) {
           
        }
    }
    
    
}
