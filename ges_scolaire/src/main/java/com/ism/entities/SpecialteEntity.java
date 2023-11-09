package com.ism.entities;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpecialteEntity {
    private  static int nbrSpecialte;
    private int id;
    private String libelle;
    public SpecialteEntity(String libelle) {
        id=++nbrSpecialte;
        this.libelle = libelle;
    }
    @Override
    public String toString() {
        return "id :\t" + id + ", libelle :\t" + libelle ;
    }
}
