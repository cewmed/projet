package com.ism.views;



import com.ism.entities.ClasseEntity;
import com.ism.entities.EnseignementEntity;
import com.ism.entities.ModuleEntity;
import com.ism.entities.ProfesseurEntity;
import com.ism.entities.SpecialteEntity;
import com.ism.repositories.ClasseRepository;
import com.ism.repositories.DataBase;
import com.ism.repositories.EnseignementReposytory;
import com.ism.repositories.ModuleReposytory;
import com.ism.repositories.ProfesseurReposytory;
import com.ism.repositories.impl.ClasseRepositoryImpl;
import com.ism.repositories.impl.EnseignementImpl;
import com.ism.repositories.impl.ModuleImpl;
import com.ism.repositories.impl.MysqlImpl;
import com.ism.repositories.impl.ProfesseurImpl;
import com.ism.services.ClasseService;
import com.ism.services.EnseignementService;
import com.ism.services.ModuleService;
import com.ism.services.ProfesseurService;
import com.ism.services.impl.ClasseServiceImpl;
import com.ism.services.impl.EnseignementServiceImpl;
import com.ism.services.impl.ModuleServiceImpl;
import com.ism.services.impl.ProfesseurServiceImpl;

public class MenuProfesseur extends BaseMenu{
   String[] menu = {"Enregistrer un Professeur","Afficher les Professeurs","Voir les [Classes,Modules] d'un Professeur","Affecter [Module,Classe] a un Prof"};
    DataBase dataBase=new MysqlImpl();
    ProfesseurReposytory professeurReposytory=new ProfesseurImpl(dataBase);
    EnseignementReposytory enseignementReposytory=new EnseignementImpl(dataBase);
       ClasseRepository classeRepository=new ClasseRepositoryImpl(dataBase);
    ModuleReposytory moduleReposytory=new ModuleImpl(dataBase);

    ClasseService classeService =new ClasseServiceImpl(classeRepository, moduleReposytory);
    ModuleService moduleService=new ModuleServiceImpl(moduleReposytory);
    ProfesseurService professeurService=new ProfesseurServiceImpl(professeurReposytory, enseignementReposytory);
    EnseignementService enseignementService=new EnseignementServiceImpl(enseignementReposytory);
   
   public int gestionMenuProf(){
        int choix;
        do {
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) +" " +fleche + menu[i]);
            }
            choix=sc.nextInt();
            switch (choix) {
                case 1:
                    sc.nextLine();
                    System.out.println("Nom & Prenom : ");
                    String nomComplet=sc.nextLine();
                    System.out.println("Email : ");
                    String email=sc.nextLine();
                    // valider email
                    boolean emailValide= professeurService.emailValidator(email.toLowerCase());
                    if (emailValide) {
                        System.out.println("Choisissez la specialite de "+nomComplet);
                        professeurService.listerSpecialite().forEach(System.out::println);
                        int idSpecialite=sc.nextInt();
                        SpecialteEntity specialite=professeurService.listerSpecialite().get(idSpecialite-1);

                        ProfesseurEntity professeurEntity =new ProfesseurEntity(0,nomComplet,email.toLowerCase(),specialite.getLibelle());
                        System.out.println("Affectez lui une classe :\n\n");
                         System.out.println("|ID|\t|NOM|\t\t|FILIERE|\t|NIVEAU|\n");
                        classeService.listerClasse().forEach(System.out::print);
                        int idCl= sc.nextInt();
                         if (classeService.findClasseById(idCl)) {
                            
                            if (classeService.listerModuleParClasse(idCl).isEmpty()) {
                                System.out.println("Cette classe n'a aucun module affectable à "+nomComplet);
                            }else{
                                System.out.println("Veuillez lui affectez un module pour cette classe:\n\n");            
                                classeService.listerModuleParClasse(idCl)
                                    .stream()
                                        .forEach(module->{
                                         System.out.println(String.format("%d => %s",module.getId(),module.getLibelle() ));});                                    
                                    int idModule=sc.nextInt(); // il saisit id 
                                    boolean idIdentiqueTrouve = classeService.listerModuleParClasse(idCl)
                                                                            .stream()
                                                                            .anyMatch(module -> idModule == module.getId());                               
                                    if (idIdentiqueTrouve) {
                                        if (professeurService.enregistrerProfesseur(professeurEntity, 
                                        new ClasseEntity(idCl), 
                                        new ModuleEntity(idModule))) {
                                            System.out.println(nomComplet+" a ete enregistrer et a une classe!");
                                        }                                        
                                    } else {
                                        System.out.println("Module introuvable!");
                                    }
                            }                          
                         }else{
                            System.out.println("Classe Introuvable "+idCl);
                        }
                    }else{
                        System.out.println("Email incorrecte : "+email);
                    }                   
                    System.out.println(LIGNE);

                    break;
            
                default:
                    break;
                case 2:
                        System.out.println("\t\t Listes des professeurs :\n");
                        String action =super.actions();
                        System.out.println(action);
                        System.out.println("|ID|\t|NOM COMPLET|\t\t\t|EMAIL|\t\t\t|SPECIALITE|\n");
                        professeurService.listerProfesseur().forEach(System.out::println); 
                        sc.nextLine();
                        action=sc.nextLine();
                        if (action.equalsIgnoreCase("X")) {
                                System.out.println("Choisissez le professeur ");
                                int idProfesseur=sc.nextInt();
                                ProfesseurEntity profEnCour=professeurService.find(idProfesseur);
                            //    
                                if (profEnCour!=null) {
                                    sc.nextLine();
                                    System.out.println("Entrer son nouveau nom :");
                                    profEnCour.setNomComplet(sc.nextLine());
                                    System.out.println("Email : ");
                                    String emailEnCour=sc.nextLine();
                                    
                                    if (professeurService.emailValidator(emailEnCour)) {
                                        profEnCour.setEmail(emailEnCour);
                                        System.out.println("Choisissez la specialite de "+profEnCour.getNomComplet());
                                        professeurService.listerSpecialite().forEach(System.out::println);
                                        int idSpecialite=sc.nextInt();
                                        SpecialteEntity specialite=professeurService.listerSpecialite().get(idSpecialite-1);
                                        profEnCour.setSpecialite(specialite.getLibelle());
                                        professeurService.modification(profEnCour);
                                    }else{
                                        System.out.println("Email invalide boy ! ;(");
                                    }
                                        
                                }else{
                                    System.out.println("Professeur introuvable");
                                }
                            }else if (action.equalsIgnoreCase("Z")){
                                System.out.println("Choisissez le professeur a archiver");
                                int idProfesseur=sc.nextInt();
                               ProfesseurEntity profEnCour=professeurService.find(idProfesseur);                           
                               if ( profEnCour !=null) {
                                      professeurService.archiver(profEnCour);
                                      System.out.println(profEnCour.getNomComplet() +" a été archiver.");
                               }else{
                                   System.out.println("Professeur introuvable");
                                }
                   }
                    // 
                        System.out.println(LIGNE);
                break;
                case 3:
                professeurService.listerProfesseur().forEach(System.out::println); 
                System.out.println("\nChoisissez l'id du prof :");
                int idProf=sc.nextInt();
                // System.out.println("\t\t Listes des professeurs :\n");
                // System.out.println("|ID|\t|NOM|\t\t|EMAIL|\t\t|SPECIALITE|\n");
                if (professeurService.listerProfesseur(idProf).isEmpty() || professeurService.listerProfesseur(idProf)==null) {
                    System.out.println("Professeur Introuvable Ou n'enseigne dans aucune classe pour le moment veuillez lui affecter une classe");
                }else{
                    professeurService.listerProfesseur(idProf).stream()
                                        .forEach(data->{
                                         System.out.println(data);});
                }
                // System.out.println("Prof introuvable");
                

                
                System.out.println(LIGNE);
                break;
                case 4:
                    professeurService.listerProfesseur().forEach(System.out::println); 
                     
                    System.out.println("\nChoisissez l'id du prof :");
                    int profId=sc.nextInt();

                    if (professeurService.rechercherProfViaId(profId)) {
                //A REVOIR   Je dois gerer le cas pour les prof archiver on affiche pas meme si il a des modules
                        System.out.println("Affectez lui une classe :\n\n");
                         System.out.println("|ID|\t|NOM|\t\t|FILIERE|\t|NIVEAU|\n");
                        classeService.listerClasse().forEach(System.out::print);
                        int idCl= sc.nextInt();
                         if (classeService.findClasseById(idCl)) {
                            
                            if (classeService.listerModuleParClasse(idCl).isEmpty()) {
                                System.out.println("Cette classe n'a aucun module affectable veuillez affecter un module a cette classe");
                            }else{
                                System.out.println("Veuillez lui affectez un module pour cette classe:\n\n");            
                                classeService.listerModuleParClasse(idCl)
                                    .stream()
                                        .forEach(module->{
                                         System.out.println(String.format("%d => %s",module.getId(),module.getLibelle() ));});                                    
                                    int idModule=sc.nextInt(); // il saisit id 
                                    boolean idIdentiqueTrouve = classeService.listerModuleParClasse(idCl)
                                                                            .stream()
                                                                            .anyMatch(module -> idModule == module.getId());                               
                                    if (idIdentiqueTrouve) {
                                        // Traitement 
                                        EnseignementEntity enseignementEntity =new EnseignementEntity(0,idCl,idModule,profId);
                                           String notif= professeurService.affecterModuleClasse(enseignementEntity) ? "L'enregistrement s'est bien déroule" : "L'enregistrement s'est mal déroule";
                                            System.out.println(notif);
                                    } else {
                                        System.out.println("Module introuvable!");
                                    }
                            }                          
                         }else{
                            System.out.println("Classe Introuvable "+idCl);
                        }
                    }else{
                        System.out.println("Professeur Introuvable!");
                    }
                break;
            }
        } while (choix != menu.length);
        return choix;
        

    }
   
}
    // A Factoriser les attribut fleche et tableau menu etc ...
