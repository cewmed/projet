package com.ism.services;

import java.util.ArrayList;

import com.ism.entities.ClasseEntity;
import com.ism.entities.CourEntity;
import com.ism.entities.ProfesseurEntity;

public interface CourService extends BaseService<CourEntity> {
     boolean enregistrerCours(CourEntity courEntity,
                            ClasseEntity classeEntity,
                            ProfesseurEntity professeurEntity
                           );
       ArrayList<CourEntity> listerCoursDeCetteClasse(int id);
}
