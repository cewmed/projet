package com.ism.services;

import java.util.ArrayList;

import com.ism.entities.ProfesseurEntity;
import com.ism.entities.SalleEntity;

public interface SalleService  extends BaseService<SalleEntity>{
    boolean enregistrerSalle(SalleEntity salleEntity);
    ArrayList<SalleEntity> listerSalles();
}
