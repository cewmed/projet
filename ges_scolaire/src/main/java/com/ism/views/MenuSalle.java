package com.ism.views;


import com.ism.entities.SalleEntity;

import com.ism.repositories.DataBase;

import com.ism.repositories.SalleReposytory;

import com.ism.repositories.impl.MysqlImpl;
import com.ism.repositories.impl.SalleImpl;

import com.ism.services.SalleService;

import com.ism.services.impl.SalleServiceImpl;

public class MenuSalle extends BaseMenu{
     private DataBase dataBase=new MysqlImpl();

    SalleReposytory salleReposytory=new SalleImpl(dataBase);
    SalleService salleService=new SalleServiceImpl(salleReposytory);




   String[] menu = {"Ajouter une nouveau salle", "Lister les salles"};
    public int gestionMenuSalle(){
        int choixMenu;
        do {
            System.out.println("\tMenu :");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) +" " +fleche + menu[i]);
            }
            choixMenu=sc.nextInt();
            switch (choixMenu) {
                case 1:
                     sc.nextLine();
                     
                     System.out.println("Entrer le libelle  :");
                       String libelle=sc.nextLine();
                    System.out.println("Entrer le nombre de place :");
                    int place=sc.nextInt();
                    String salleLibelle= "Salle "+libelle;
                     SalleEntity salleEntity=new SalleEntity(0,salleLibelle,place,false);
                    //  System.out.println(moduleEntity);
                    String notif= salleService.enregistrerSalle(salleEntity) ? "Enregistrement réussi" : "erreur d'enregistrement";
                     System.out.println(notif);
                    break;
                    case 2:
                    System.out.println("\t\t\tListe des Salles \n");
                    // System.out.println("|ID|\t|LIBELLE|\t\t|PLACE|\t\n");
                        salleService.listerSalles()
                        .stream()
                        .forEach(module->{
                         System.out.println(String.format("%d => %s Nombre de place : %d",module.getId(),module.getLibelle(),module.getPlace() ));});
                        break;
            
                default:
                    System.out.println("Vous avez quitter le programme");
                    break;
            }
        } while (choixMenu != menu.length);
                   
       
        
        return choixMenu;
    }
}
