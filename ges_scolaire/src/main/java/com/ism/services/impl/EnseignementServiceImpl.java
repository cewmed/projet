package com.ism.services.impl;

import com.ism.entities.EnseignementEntity;
import com.ism.repositories.EnseignementReposytory;
import com.ism.services.EnseignementService;

public class EnseignementServiceImpl implements EnseignementService{
    private EnseignementReposytory enseignementReposytory;

    public EnseignementServiceImpl(EnseignementReposytory enseignementReposytory) {
        this.enseignementReposytory = enseignementReposytory;
    }

    @Override
    public boolean affecte(EnseignementEntity enseignementEntity) {
        return false;
    }
}
