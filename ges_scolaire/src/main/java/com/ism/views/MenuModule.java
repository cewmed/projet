package com.ism.views;

import java.util.Scanner;


import com.ism.entities.ModuleEntity;
import com.ism.repositories.ClasseRepository;
import com.ism.repositories.DataBase;
import com.ism.repositories.ModuleReposytory;
import com.ism.repositories.impl.ClasseRepositoryImpl;
import com.ism.repositories.impl.ModuleImpl;
import com.ism.repositories.impl.MysqlImpl;
import com.ism.services.ClasseService;
import com.ism.services.ModuleService;
import com.ism.services.impl.ClasseServiceImpl;
import com.ism.services.impl.ModuleServiceImpl;

public class MenuModule {
     private DataBase dataBase=new MysqlImpl();
    ClasseRepository classeRepository=new ClasseRepositoryImpl(dataBase);
    ModuleReposytory moduleReposytory=new ModuleImpl(dataBase);
    ClasseService classeService =new ClasseServiceImpl(classeRepository, moduleReposytory);
    ModuleService moduleService=new ModuleServiceImpl(moduleReposytory);



   static Scanner sc = new Scanner(System.in);
   final String fleche = "---------->\t";
   String[] menu = {"Creer un nouveau module", "Lister les Modules"};
    public int gestionModuleMenu(){
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
                     
                     System.out.println("Entrer le libelle du module :");
                       String libelle=sc.nextLine();
                    
                     ModuleEntity moduleEntity=new ModuleEntity(0,libelle);
                    //  System.out.println(moduleEntity);
                     Boolean ok=moduleService.creerModule(moduleEntity);
                     if (ok) {
                        System.out.println(libelle + " "+" a bien ete creer ");
                     }else{
                        System.out.println("erreur de creation ");
                     }
                    break;
                    case 2:
                    System.out.println("\t\t\tListe des modules \n");
                    System.out.println("|ID|\t|LIBELLE|\n");
                        moduleService.listerModules().forEach(System.out::print);
                        break;
            
                default:
                    System.out.println("Vous avez quitter le programme");
                    break;
            }
        } while (choixMenu != menu.length);
                   
       
        
        return choixMenu;
    }
}
