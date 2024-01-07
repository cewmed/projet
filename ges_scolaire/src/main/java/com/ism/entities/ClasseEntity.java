package com.ism.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClasseEntity {

    private int id;
    private String nom;
    private String filiere;
    private String niveau;
    private Boolean isArchived;
         

    
   public ClasseEntity(String nom, String filiere, String niveau) {
      this.nom = nom;
      this.filiere = filiere;
      this.niveau = niveau;
    }

  public ClasseEntity(String nom) {
      this.nom = nom;
    }

  public ClasseEntity(int id, String nom, String filiere, String niveau) {
  this.id = id;
  this.nom = nom;
  this.filiere = filiere;
  this.niveau = niveau;
  // this.module_id = module_id;
}

  public  ClasseEntity(int id){
      this.id=id;
    }

  @Override
  public String toString() {
    // return String.format("| %-3s | %-20s | %-30s | %-30s |",
    // id, nom, filiere, niveau);
    return ""+id +"\t" + nom +"\t\t"+"  "+filiere+"\t\t"+"   "+niveau+"\t"+ "\n"+"\n";
    // return "id = " + id + ", nom = " + nom + ", filiere = " + filiere + ", niveau = " + niveau + ", module_id = "
    //     + module_id + "\n";
  }


    
}
