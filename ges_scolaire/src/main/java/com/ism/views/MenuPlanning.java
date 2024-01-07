package com.ism.views;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ism.entities.ClasseEntity;
import com.ism.entities.CourEntity;
import com.ism.entities.ModuleEntity;
import com.ism.entities.PlanningEntity;
import com.ism.entities.ProfesseurEntity;
import com.ism.entities.SalleEntity;
import com.ism.repositories.AffectationReposytory;
import com.ism.repositories.ClasseRepository;
import com.ism.repositories.CourReposytory;
import com.ism.repositories.DataBase;
import com.ism.repositories.EnseignementReposytory;
import com.ism.repositories.ModuleReposytory;
import com.ism.repositories.PlanningReposytory;
import com.ism.repositories.ProfesseurReposytory;
import com.ism.repositories.SalleReposytory;
import com.ism.repositories.impl.AffectationImpl;
import com.ism.repositories.impl.ClasseRepositoryImpl;
import com.ism.repositories.impl.CourImpl;
import com.ism.repositories.impl.EnseignementImpl;
import com.ism.repositories.impl.ModuleImpl;
import com.ism.repositories.impl.MysqlImpl;
import com.ism.repositories.impl.PlanningImpl;
import com.ism.repositories.impl.ProfesseurImpl;
import com.ism.repositories.impl.SalleImpl;
import com.ism.services.AffectationService;
import com.ism.services.ClasseService;
import com.ism.services.CourService;
import com.ism.services.ModuleService;
import com.ism.services.ProfesseurService;
import com.ism.services.SalleService;
import com.ism.services.impl.AffectationServiceImpl;
import com.ism.services.impl.ClasseServiceImpl;
import com.ism.services.impl.CourServiceImpl;
import com.ism.services.impl.ModuleServiceImpl;
import com.ism.services.impl.ProfesseurServiceImpl;
import com.ism.services.impl.SalleServiceImpl;

public class MenuPlanning extends BaseMenu{
    private DataBase dataBase=new MysqlImpl();
     // REPOSYTORY
    ClasseRepository classeRepository=new ClasseRepositoryImpl(dataBase);
    ModuleReposytory moduleReposytory=new ModuleImpl(dataBase);
    AffectationReposytory affectationReposytory=new AffectationImpl(dataBase);
  ProfesseurReposytory professeurReposytory=new ProfesseurImpl(dataBase);
    EnseignementReposytory enseignementReposytory = new EnseignementImpl(dataBase);
    SalleReposytory salleReposytory = new SalleImpl(dataBase);
    CourReposytory courReposytory = new CourImpl(dataBase);
    PlanningReposytory planningReposytory=new PlanningImpl(dataBase);
    // FIN REPOSYTORY

    ClasseService classeService =new ClasseServiceImpl(classeRepository, moduleReposytory);
    ModuleService moduleService=new ModuleServiceImpl(moduleReposytory);
    ProfesseurService professeurService =new ProfesseurServiceImpl(professeurReposytory, enseignementReposytory);
    AffectationService affectationService=new AffectationServiceImpl(affectationReposytory);
    SalleService salleService = new SalleServiceImpl(salleReposytory);
    CourService courService=new CourServiceImpl(courReposytory, planningReposytory);
    String[] menu = {"Planifier un cours","Lister les cours d'une classe","Voir les cours d'un professeur"};
    public int gestionPlanningMenu(){
        int choixMenu;
        do {
            System.out.println("\tMenu :");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) +" " +fleche + menu[i]);
            }
            choixMenu=sc.nextInt();
            switch (choixMenu) {
                case 1:
                    // Lister classe puis choisir
                    System.out.println("|ID|\t|NOM|\t\t|FILIERE|\t|NIVEAU|\n");
                    classeService.listerClasse().forEach(System.out::print);
                    sc.nextLine();
                    System.out.println("Choisissez une classe pour le cour :");
                    int idClasse= sc.nextInt();
                   ClasseEntity classeEnCour=classeService.find(idClasse);
                  
                    if (classeEnCour!=null) {
                        classeService.listerModuleParClasse(classeEnCour.getId())
                            .stream()
                                .forEach(module->{
                                System.out.println(String.format("%d => %s",module.getId(),module.getLibelle() ));
                                
                        });  

                         System.out.println("\nChoisissez le module qui sera enseigner :");                                  
                        int idModule=sc.nextInt(); // il saisit id du module
                        
                        boolean idIdentiqueTrouve = classeService.listerModuleParClasse(classeEnCour.getId())
                                                                .stream()
                                                                .anyMatch(module -> idModule == module.getId());  
                        if (idIdentiqueTrouve) {
                            int currentIdModule = idIdentiqueTrouve ? idModule : 0;
                               ModuleEntity moduleEnCour = moduleService.find(currentIdModule);
                                if (moduleEnCour != null) {
                                    System.out.println("Voici le/les profs qui enseigne : "+moduleEnCour.getLibelle()+"\n");
                                }

                            professeurService.listerProfesseurParModule(currentIdModule).stream()
                                                                                        .forEach(p->{
                                                                                        System.out.println(String.format("%d => %s",p.getId(),p.getNomComplet() ));}); ; 
                             System.out.println("\nChoisissez le professeur qui dispensera : "+moduleEnCour.getLibelle()); 
                             int idProfesseur=sc.nextInt(); 
                             boolean profTrouve =professeurService.listerProfesseurParModule(currentIdModule)
                                                                   .stream()
                                                                .anyMatch(p -> idProfesseur == p.getId()); 
                            if (profTrouve) {
                                int currentProfId = profTrouve ? idProfesseur : 0;
                               ProfesseurEntity currentProfessor= professeurService.find(currentProfId);
                                //    System.out.println(currentProfessor.getNomComplet());
                                   CourEntity courEntity = new CourEntity();
                                   courEntity.setLibelle(moduleEnCour.getLibelle());
                                   System.out.println("\t\t\tListe des Salles \n");
                                   // System.out.println("|ID|\t|LIBELLE|\t\t|PLACE|\t\n");
                                       salleService.listerSalles().stream()
                                                                .forEach(salle->{
                                                                System.out.println(String.format("%d => %s",salle.getId(),salle.getLibelle() ));});
                                        System.out.println("\nChoisissez la salle : ");
                                        int idSalle =sc.nextInt();
                                        boolean salleTrouve = salleService.listerSalles().stream() .anyMatch(salle -> idSalle == salle.getId());
                                        int currentSalleOfClasse=salleTrouve ? idSalle :0; 
                                        SalleEntity salle = salleService.find(currentSalleOfClasse);
                                        courEntity.setSalleDeCour(salle.getLibelle());
                                        sc.nextLine();
                                        System.out.print("Veuillez saisir une date (format: JJ-MM-AAAA) : ");
                                        String dateString = sc.nextLine();

                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                        LocalDate date = LocalDate.parse(dateString, formatter);
                                        LocalDate currentDate = LocalDate.now();
                                        if (date.isBefore(currentDate)) {
                                            System.out.println("La date saisie est déjà dépassée.");
                                        } else {
                                            courEntity.setDate(date);

                                            System.out.println("Heure de debut au format hh-mm : ");
                                            String heure =sc.nextLine();
                                            DateTimeFormatter heureFormatter = DateTimeFormatter.ofPattern("HH-mm");
                                            try {
                                            
                                                LocalTime beginHour = LocalTime.parse(heure, heureFormatter);
                                                // Vérification des heures et des minutes
                                                if (beginHour.getHour() >= 0 && beginHour.getHour() <= 23 && beginHour.getMinute() >= 0 && beginHour.getMinute() <= 59) {
                                                    
                                                    courEntity.setDebutHeure(beginHour);
                                                } else {
                                                    System.out.println("L'heure saisie est incorrecte.");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("L'heure saisie est incorrecte.");
                                            }
                                            System.out.println("Heure de fin au format hh-mm : ");
                                            String heureDeFIn =sc.nextLine();
                                            try {
                                            
                                                LocalTime endHour = LocalTime.parse(heureDeFIn, heureFormatter);
                                                // Vérification des heures et des minutes
                                                if (endHour.getHour() >= 0 && endHour.getHour() <= 23 && endHour.getMinute() >= 0 && endHour.getMinute() <= 59) {
                                                    // System.out.println(" saisie est valide : " + endHour);
                                                     courEntity.setFinHeure(endHour);
                                                    if (courService.enregistrerCours(courEntity,new ClasseEntity(classeEnCour.getId()), new ProfesseurEntity(currentProfessor.getId()))){
                                                            System.out.println("ok");
                                                    } 
                                                     
                                                    
                                                } else {
                                                    System.out.println("L'heure saisie est incorrecte.");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("L'heure saisie est incorrecte.");
                                            }
                                             
                                        }
                                        

                            }
                        }else{
                            System.out.println("pas bon");
                        }
                       

                     
                    //     if (moduleDeLaClasse.isEmpty()) {
                    //             System.out.println(classeEnCour.getNom()+" n'a aucun module");
                    //     }else{
                    //             for (ModuleEntity mod : moduleDeLaClasse) {
                    //                      System.out.println("module de la classe :" +mod.getLibelle());  
                    //                      System.out.println("prof associer a ce module :");
                    //                     int  moduleId=mod.getId();
                    //                     for (ProfesseurEntity profEntity : moduleOfProfesseur) {
                    //                         System.out.println(profEntity);
                    //                     }
                    //              }
                                 
                                 
                                 
                    //     }
                    }else{
                        System.out.println("Classe Introuvable "+idClasse);
                    }
                    break;

                    case 2:
                             // Lister classe puis choisir
                        System.out.println("|ID|\t|NOM|\t\t|FILIERE|\t|NIVEAU|\n");
                        classeService.listerClasse().forEach(System.out::print);
                        sc.nextLine();
                        System.out.println("Choisissez la classe :");
                        int classeId= sc.nextInt();
                        ClasseEntity currentCl=classeService.find(classeId);
                        if (currentCl != null) {
                            if (courService.listerCoursDeCetteClasse(classeId)==null) {
                                System.out.println("La "+currentCl.getNom()+" na aucun cour de prévue");
                            }else{
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                courService.listerCoursDeCetteClasse(classeId).stream()
                                .forEach(cour->{
                                 System.out.println(String.format("%s %s %s %s %s","Module enseigner : "+cour.getLibelle()+"\t",
                                                                                    "Date : "+cour.getDate().format(formatter)+"\t"
                                                                                    ,"Salle : "+ cour.getSalleDeCour()+"\t"
                                                                                    ,"Heure : "+cour.getDebutHeure()+"\t"
                                                                                    ,"Heure de Fin : "+cour.getFinHeure()+"\n" ));
                            });;
                            }
                        }else{
                            System.out.println("Classe Introuvable.");
                        }
                    break;

                    case 3:
                    System.out.println("\t\t Listes des professeurs :\n");
                    System.out.println("|ID|\t|NOM COMPLET|\t\t\t|EMAIL|\t\t\t|SPECIALITE|\n");
                    professeurService.listerProfesseur().forEach(System.out::println); 
                    System.out.println("Entrer l'id du prof :");
                    int professeurId = sc.nextInt();
                    // sc.nextLine();
                      ProfesseurEntity prof=professeurService.find(professeurId);
                    if (prof != null) {
                        if (professeurService.listerSesCours(professeurId).isEmpty()) {
                            System.out.println(prof.getNomComplet() +" n'a aucun cours.");
                        }else{
                             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            professeurService.listerSesCours(professeurId).stream()
                                .forEach(cour->{
                                 System.out.println(String.format("%s %s %s %s %s","Module enseigner : "+cour.getLibelle()+"\t",
                                                                                    "Date : "+cour.getDate().format(formatter)+"\t"
                                                                                    ,"Salle : "+ cour.getSalleDeCour()+"\t"
                                                                                    ,"Heure : "+cour.getDebutHeure()+"\t"
                                                                                    ,"Heure de Fin : "+cour.getFinHeure()+"\n" ));
                            });;
                        }
                    }
                    
                    break;
                    
            
                default:
                    System.out.println("Vous avez quitter le programme");
                    break;
            }
        } while (choixMenu != menu.length);
                   
       
        
        return choixMenu;
    }
}
