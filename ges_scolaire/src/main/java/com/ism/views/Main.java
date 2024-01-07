package com.ism.views;

import java.util.Scanner;

import com.ism.repositories.AuthReposytory;
import com.ism.repositories.DataBase;
import com.ism.repositories.impl.AuthImpl;
import com.ism.repositories.impl.MysqlImpl;
import com.ism.services.AuthService;
import com.ism.services.impl.AuthServiceImpl;

public class Main extends Utils{
    public static void main(String[] args) {
      
        int choix;  
       
         String[] menu = {menuItemStart+"Classes\n", menuItemStart+"Modules\n", menuItemStart+"Professeurs\n",menuItemStart+"Salles\n",menuItemStart+"Plannings"};
        
        // INJECTION DE DEPENDANCE
        DataBase dataBase = new MysqlImpl();
        AuthReposytory authReposytory = new AuthImpl(dataBase);
        AuthService authService = new AuthServiceImpl(authReposytory);
        // FIN INJECTION DE DEP
        do {
          System.out.println("\tMenu :");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) +" " +fleche + menu[i]);
        }
          choix=sc.nextInt();
            // choix = gestionClasseMenu();
            switch (choix) {
                case 1:
                    // System.out.println("vous ete au cas 1");
                    MenuClasse menuClasse = new MenuClasse();
                    //   sc.nextLine();
                    // System.out.println("login :");
                    // String login = sc.nextLine();
                    // System.out.println("cle :");
                    // String cle =sc.nextLine();
                    // if (authService.authentification(login.toLowerCase(),cle.toLowerCase())) {
                    //     // System.out.println("ok");
                    //       choix= menuClasse.gestionClasseMenu();
                    // } else{
                    //     System.out.println("Acces permanament refuser !");
                    // }
                    choix= menuClasse.gestionClasseMenu();

                    
                    break;
                case 2:
                    System.out.println("---------------------------------------------------------------------------------------------");
                    MenuModule menuModule=new MenuModule();
                    choix = menuModule.gestionModuleMenu();
                    break;
                case 3:
                    MenuProfesseur menuProfesseur=new MenuProfesseur();
                    choix=menuProfesseur.gestionMenuProf();
                    break;
                case 4:
                    MenuSalle menuSalle=new MenuSalle();
                    choix=menuSalle.gestionMenuSalle();
                    break;
                case 5:
                    MenuPlanning menuPlanning =new MenuPlanning();
                    choix=menuPlanning.gestionPlanningMenu();
                    break;
            
                default:
                sc.close();
                    break;
            }
        } while (choix != menu.length);
    }
}