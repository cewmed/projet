    package com.ism.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor

public class CourEntity {
    private int id;
    private String libelle;
    private String salleDeCour;
    private LocalDate date;
    private LocalTime debutHeure;
    private LocalTime finHeure;

    private SalleEntity salleEntity;
    private ProfesseurEntity professeurEntity;
    private  ArrayList<ClasseEntity> classes;
    public CourEntity(int id, String libelle, String salleDeCour, LocalDate date, LocalTime debutHeure,
            LocalTime finHeure) {
        this.id = id;
        this.libelle = libelle;
        this.salleDeCour = salleDeCour;
        this.date = date;
        this.debutHeure = debutHeure;
        this.finHeure = finHeure;
    } 


}
