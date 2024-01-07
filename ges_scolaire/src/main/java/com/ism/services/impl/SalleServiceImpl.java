package com.ism.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.ism.entities.ClasseEntity;
import com.ism.entities.ProfesseurEntity;
import com.ism.entities.SalleEntity;
import com.ism.repositories.SalleReposytory;
import com.ism.services.SalleService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class SalleServiceImpl implements  SalleService{
    private SalleReposytory salleReposytory;

    @Override
    public boolean enregistrerSalle(SalleEntity salleEntity) {
        return salleReposytory.insert(salleEntity)!=0;
    }

    @Override
    public ArrayList<SalleEntity> listerSalles() {
        return salleReposytory.findAll();
    }


    @Override
    public SalleEntity find(int id) {
        List<SalleEntity> tabClasses = salleReposytory.findAll();
        for (SalleEntity salle : tabClasses) {
            if (salle.getId()==id) {
                return salle;
            }      
        }
        return null;
    
    }

    @Override
    public boolean modification(SalleEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modification'");
    }

    @Override
    public boolean archiver(SalleEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiver'");
    }
    
}
