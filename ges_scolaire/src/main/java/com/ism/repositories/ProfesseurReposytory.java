package com.ism.repositories;

import java.util.ArrayList;

import com.ism.entities.CourEntity;
import com.ism.entities.ProfesseurEntity;

public interface ProfesseurReposytory extends BaseReposytory<ProfesseurEntity>{
    ArrayList<Object> showDataByProfId(int id);
    ArrayList<ProfesseurEntity> findById(int id);
    ArrayList<ProfesseurEntity> findProfByModuleId(int id);
    ArrayList<CourEntity> findCoursByProf(int id);
}
