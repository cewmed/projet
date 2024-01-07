package com.ism.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import com.ism.entities.ClasseEntity;
import com.ism.entities.CourEntity;
import com.ism.entities.ModuleEntity;
import com.ism.entities.PlanningEntity;
import com.ism.entities.ProfesseurEntity;
import com.ism.repositories.CourReposytory;
import com.ism.repositories.PlanningReposytory;
import com.ism.services.CourService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class CourServiceImpl implements CourService{
    private CourReposytory courReposytory;
    private PlanningReposytory planningReposytory;
    @Override
    public boolean modification(CourEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modification'");
    }

    @Override
    public boolean archiver(CourEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiver'");
    }

    @Override
    public CourEntity find(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public boolean enregistrerCours(CourEntity courEntity,
                                                ClasseEntity classeEntity,
                                                 ProfesseurEntity professeurEntity) {
        if (courEntity.getId()==0) {
            int courId= courReposytory.insert(courEntity);
            courEntity.setId(courId);

        }
        PlanningEntity planning=new PlanningEntity(0,classeEntity.getId(),courEntity.getId(),professeurEntity.getId());
        return planningReposytory.insert(planning)!=0;
    }

    @Override
    public ArrayList<CourEntity> listerCoursDeCetteClasse(int id) {
        ArrayList<CourEntity> tabCours = courReposytory.findCoursByClasse(id);
        LocalDate toDay = LocalDate.now();
        for (CourEntity cour : tabCours) {
                if (!cour.getDate().isBefore(toDay)) {
                        return tabCours;    
                }
        }
        return null;
    }

}
