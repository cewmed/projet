package com.ism.services;

import java.util.ArrayList;

// import com.ism.entities.ClasseEntity;
import com.ism.entities.ModuleEntity;


public interface ModuleService extends BaseService<ModuleEntity>{
    boolean creerModule(ModuleEntity moduleEntity);
    boolean verifierLibelle(ModuleEntity moduleEntity);
    boolean verifierId(int id);
      ArrayList<ModuleEntity> listerModules();
}
