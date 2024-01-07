package com.ism.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ism.entities.ClasseEntity;
import com.ism.entities.CourEntity;
import com.ism.entities.EnseignementEntity;
import com.ism.entities.ModuleEntity;
import com.ism.entities.ProfesseurEntity;
import com.ism.entities.SpecialteEntity;
import com.ism.repositories.EnseignementReposytory;
import com.ism.repositories.ProfesseurReposytory;
import com.ism.services.ProfesseurService;
import java.util.regex.Pattern;

public class ProfesseurServiceImpl implements ProfesseurService{
    private ProfesseurReposytory professeurReposytory;
    private EnseignementReposytory enseignementReposytory;
    ProfesseurEntity professeurEntity;

    public ProfesseurServiceImpl(ProfesseurReposytory professeurReposytory,
            EnseignementReposytory enseignementReposytory) {
        this.professeurReposytory = professeurReposytory;
        this.enseignementReposytory = enseignementReposytory;
    }

    @Override
    public List<SpecialteEntity> listerSpecialite() {
         return Arrays.asList(new SpecialteEntity("Concepteur d'application"),
                                new SpecialteEntity("Science & Mathematique"),
                                new SpecialteEntity("Langue etrangère")
                                ,new SpecialteEntity("Psychologie")
                                ,new SpecialteEntity("Economie")
                                ,new SpecialteEntity("Philosophie"));
    }

    @Override
    public boolean enregistrerProfesseur(ProfesseurEntity professeurEntity,ClasseEntity classe,ModuleEntity module) {
        if (professeurEntity.getId()==0) {
            int idProf= professeurReposytory.insert(professeurEntity);
            professeurEntity.setId(idProf);

        }
        // return professeurReposytory.insert(professeurEntity)!=0;
        EnseignementEntity enseignementEntity=new EnseignementEntity(0,classe.getId(),module.getId(),professeurEntity.getId());
        return enseignementReposytory.insert(enseignementEntity)!=0;
    }

    @Override
    public boolean emailValidator(String email) {
        String gmailRegex = ".*@gmail\\.com$";
        return Pattern.matches(gmailRegex, email);
    }

    @Override
    public ArrayList<ProfesseurEntity> listerProfesseur() {
        return professeurReposytory.findAll();
    }

    @Override
    public ArrayList<Object> listerProfesseur(int id) {
        return professeurReposytory.showDataByProfId(id);
    }

    @Override
    public boolean rechercherProfViaId(int id) {
        ArrayList<ProfesseurEntity> tabProf= professeurReposytory.findById(id);
        for (ProfesseurEntity mod : tabProf) {
            if (mod.getId()==id) {
                return true;
            }
            
        }
        return false;
    }

    @Override
    public boolean affecterModuleClasse(EnseignementEntity data) {
       return enseignementReposytory.insert(data)!=0;
    }

    @Override
    public boolean modification(ProfesseurEntity data) {
       return professeurReposytory.update(data)!=0;
    }

    @Override
    public boolean archiver(ProfesseurEntity data) {
        return professeurReposytory.archiver(data) !=0;
    }

    @Override
    public ProfesseurEntity find(int id) {
        ArrayList<ProfesseurEntity> profs = professeurReposytory.findAll();
        for (ProfesseurEntity prof : profs) {
                if (prof.getId() == id) {
                        return prof;    
                }
        }
        return null;
    }

    @Override
    public ArrayList<ProfesseurEntity> listerProfesseurParModule(int id) {
        return professeurReposytory.findProfByModuleId(id);
    }

    @Override
    public ArrayList<CourEntity> listerSesCours(int id) {
         return professeurReposytory.findCoursByProf(id);
    }
    
}
