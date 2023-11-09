package com.ism.services;

import java.util.ArrayList;
import java.util.List;

import com.ism.entities.ClasseEntity;
import com.ism.entities.EnseignementEntity;
import com.ism.entities.ModuleEntity;
import com.ism.entities.ProfesseurEntity;
import com.ism.entities.SpecialteEntity;

public interface ProfesseurService {
    boolean enregistrerProfesseur(ProfesseurEntity professeurEntity,ClasseEntity classe,ModuleEntity module);
    List<SpecialteEntity> listerSpecialite();
    ArrayList<ProfesseurEntity> listerProfesseur();
    ArrayList<Object> listerProfesseur(int id);
    boolean rechercherProfViaId(int id);
    boolean emailValidator(String email);
    boolean affecterModuleClasse(EnseignementEntity data);

}
