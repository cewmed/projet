package com.ism.views;

import java.util.Scanner;

public  abstract class BaseMenu {
     Scanner sc = new Scanner(System.in);
     
    // protected String[] menu  = {"Ajouter une classe", "Lister les classes","Affecter un module a une classe","Lister les modules d'une classe","Lister les Classes qui font un module specifique","Quitter"};
   protected final String fleche = "---------->\t";
   protected final String LIGNE = "-----------------------------------------------------------------------------------------------------------------------------";
    
    
    
    
    
    protected String actions(){
        return"->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Actions [X => Modifier / Z =>Archiver]\n";
    }
}
