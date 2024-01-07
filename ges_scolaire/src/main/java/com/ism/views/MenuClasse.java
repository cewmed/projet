package com.ism.views;

import java.util.Scanner;

import com.ism.entities.AffectationEntity;
import com.ism.entities.ClasseEntity;
import com.ism.entities.FiliereEntity;
import com.ism.entities.NiveauEntity;
import com.ism.repositories.AffectationReposytory;
import com.ism.repositories.ClasseRepository;
import com.ism.repositories.DataBase;
import com.ism.repositories.ModuleReposytory;
import com.ism.repositories.impl.AffectationImpl;
import com.ism.repositories.impl.ClasseRepositoryImpl;
import com.ism.repositories.impl.ModuleImpl;
import com.ism.repositories.impl.MysqlImpl;
import com.ism.services.AffectationService;
import com.ism.services.ClasseService;
import com.ism.services.ModuleService;
import com.ism.services.impl.AffectationServiceImpl;
import com.ism.services.impl.ClasseServiceImpl;
import com.ism.services.impl.ModuleServiceImpl;

public class MenuClasse extends BaseMenu{
    private DataBase dataBase=new MysqlImpl();
    // REPOSYTORY
    ClasseRepository classeRepository=new ClasseRepositoryImpl(dataBase);
    ModuleReposytory moduleReposytory=new ModuleImpl(dataBase);
    AffectationReposytory affectationReposytory=new AffectationImpl(dataBase);
    // FIN REPOSYTORY

    ClasseService classeService =new ClasseServiceImpl(classeRepository, moduleReposytory);
    ModuleService moduleService=new ModuleServiceImpl(moduleReposytory);
    AffectationService affectationService=new AffectationServiceImpl(affectationReposytory);

   static Scanner sc = new Scanner(System.in);
   
   String[] menu = {"Ajouter une classe", "Lister les classes","Affecter un module a une classe","Lister les modules d'une classe","Lister les Classes qui font un module specifique","Quitter"};
    
    public int gestionClasseMenu(){

        int choixMenu;
        do {
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) +" " +fleche + menu[i]);
            }
            choixMenu=sc.nextInt();
            switch (choixMenu) {
            case 1:
            sc.nextLine();
            System.out.println("Choisir une Filiere");
            classeService.listerFiliere().forEach(System.out::println);
            int idFiliere=sc.nextInt();

            System.out.println("Choisir une Niveaux");
            classeService.listerNiveaux().forEach(System.out::println);
            int idNiveau=sc.nextInt();
            sc.nextLine();
             FiliereEntity filiereSelect=classeService.listerFiliere().get(idFiliere-1);
                     NiveauEntity niveauSelect=classeService.listerNiveaux().get(idNiveau-1);
                     String nom=String.format("%s %s",niveauSelect.getLibelle(),filiereSelect.getLibelle());
             ClasseEntity classe=new ClasseEntity(0,nom,filiereSelect.getLibelle(),niveauSelect.getLibelle());
            //    System.out.println(classe);
            if (classeService.ajouterClasse(classe)) {
                System.out.println("La classe a ete enregistrer  !");
            }else{
                System.out.println("Erreur d'insertion !");
            }
                // classeService.ajouterClasse(classe);
                
                System.out.println(LIGNE);
                gestionClasseMenu();
                break;
            case 2:
                System.out.println("\t\t Listes des classes :\n");
                String action =super.actions();
                System.out.println(action);
                System.out.println("|ID|\t|NOM|\t\t|FILIERE|\t|NIVEAU|\n");
                classeService.listerClasse().forEach(System.out::print);


                sc.nextLine();
                 action=sc.nextLine();
                if (action.equalsIgnoreCase("X")) {

                     System.out.println("Choisissez la classe");
                     int idClasse=sc.nextInt();
                    ClasseEntity classeEnCour = classeService.find(idClasse);
                     if ( classeEnCour !=null) {
                            System.out.println("Choisissez la nouvelle Filiere");
                            classeService.listerFiliere().forEach(System.out::println);
                            
                            int newFiliere=sc.nextInt();
                            FiliereEntity newFiliereSelect=classeService.listerFiliere().get(newFiliere-1);

                            System.out.println("Choisissez le nouveau Niveau");
                            classeService.listerNiveaux().forEach(System.out::println);
                            int newNiveau=sc.nextInt();
                             NiveauEntity newNiveauSelect=classeService.listerNiveaux().get(newNiveau-1);
                            sc.nextLine();
                            
                           
                            String newNom=String.format("%s %s",newNiveauSelect.getLibelle(),newFiliereSelect.getLibelle());
                            classeEnCour.setNom(newNom);
                            classeEnCour.setFiliere(newFiliereSelect.getLibelle());
                            classeEnCour.setNiveau(newNiveauSelect.getLibelle());
                            classeService.modification(classeEnCour);
                              
                        }else{
                            System.out.println("Classe introuvable");
                     }
                     
                }else if (action.equalsIgnoreCase("Z")){
                             System.out.println("Choisissez la classe");
                            int idClasse=sc.nextInt();
                            ClasseEntity classeEnCour = classeService.find(idClasse);
                            if ( classeEnCour !=null) {
                                   classeService.archiver(classeEnCour);
                            }else{
                                System.out.println("Classe introuvable");
                             }
                }

                // System.out.println(LIGNE);
                // gestionClasseMenu();
                break;
            case 3:
                System.out.println("Choisissez la classe :\n\n");
                classeService.listerClasse().forEach(System.out::print);
                int idClasse= sc.nextInt();
                if (classeService.findClasseById(idClasse)) {
                    sc.nextLine();
                    System.out.println("Choisissez le module a affecter :\n\n");
                    moduleService.listerModules().forEach(System.out::print);
                    int idModule= sc.nextInt();
                    if (classeService.findByModuleId(idModule)) {
                        // inserer dans affectation
                        AffectationEntity affectationEntity=new AffectationEntity(0, idClasse, idModule);             
                        
                        if (affectationService.affecterModule(affectationEntity)) {
                            System.out.println(" Nickel !");
                        }
                    }else{
                        System.out.println(" Ce module n'existe pas");
                    }
                }else{
                    System.out.println("Cette classe n'existe pas");
                }
                System.out.println(LIGNE);
                gestionClasseMenu();
                break;
                case 4:
                         System.out.println("Choisissez la classe :\n\n");
                         System.out.println("|ID|\t|NOM|\t\t|FILIERE|\t|NIVEAU|\n");
                        classeService.listerClasse().forEach(System.out::print);
                        int idCl= sc.nextInt();
                         if (classeService.findClasseById(idCl)) {
    
                            if (classeService.listerModuleParClasse(idCl).isEmpty()) {
                                System.out.println("Cette classe n'a aucun module ");
                            }else{
                                System.out.println("Cette Classe a les modules suivants :\n"); 
                                classeService.listerModuleParClasse(idCl)
                                    .stream()
                                        .forEach(module->{
                                         System.out.println(String.format("=> %s",module.getLibelle() ));
                                    });

                            }
                             
                         }else{
                            System.out.println("Classe Introuvable "+idCl);
                        }   
                        System.out.println(LIGNE);   
                        gestionClasseMenu(); 
                break;
                case 5:
                         System.out.println("Choisissez un module  :\n\n");
                        moduleService.listerModules().forEach(System.out::print);
                        int moduleChoisi= sc.nextInt();
                        if (classeService.findByModuleId(moduleChoisi)) {
                                System.out.println("Voici la Liste des Classes ayant ce module \n");
                                System.out.println("|NOM|\t\t|FILIERE|\t|NIVEAU|\n");
                                if (classeService.listerClasseParModule(moduleChoisi).isEmpty()) {
                                     System.out.println("Cette classe n'a aucun module ");
                                }else{
                                    classeService.listerClasseParModule(moduleChoisi).stream()
                                        .forEach(module->{
                                         System.out.println(String.format("=> %s",module.getNom()+"\t"+"  "+module.getFiliere()+"\t\t"+"   "+module.getNiveau() ));
                                         ;
                                    });
                                }
                                
                        }else{
                            System.out.println("Ce identifiant existe pas");
                        }
                        System.out.println(LIGNE);
                        gestionClasseMenu(); 
                break;

            
        
            default:

                break;
        }
        } while (choixMenu != menu.length);
        
        return choixMenu;
    }
    
}

