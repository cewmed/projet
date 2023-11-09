package com.ism.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesseurEntity {
    private int id;
    private String nomComplet;
    private String email;
    private String specialite;

    public ProfesseurEntity(int id, String nomComplet) {
        this.id = id;
        this.nomComplet = nomComplet;
    }
    public ProfesseurEntity(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        // return ""+id +"\t" + nomComplet +"\t\t"+"  "+email+"\t\t"+"   "+specialite+"\t"+ "\n"+"\n";
        // return " " + id + "  " + nomComplet + "  " + email + " \t\t "
        //         + specialite + " \n\n";
        return String.format("| %-3s | %-20s | %-30s | %-30s |",
        id, nomComplet, email, specialite);

    }
}
