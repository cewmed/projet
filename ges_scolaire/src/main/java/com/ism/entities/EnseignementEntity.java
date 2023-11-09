package com.ism.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// @AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnseignementEntity {
    private int id;
    private int classe_id;
    private int module_id;
    private int prof_id;
    private ProfesseurEntity professeurEntity;
    public EnseignementEntity(int id, int classe_id, int module_id, int prof_id) {
        this.id = id;
        this.classe_id = classe_id;
        this.module_id = module_id;
        this.prof_id = prof_id;
    }
    public EnseignementEntity(int id, int classe_id, int module_id, ProfesseurEntity professeurEntity) {
        this.id = id;
        this.classe_id = classe_id;
        this.module_id = module_id;
        this.professeurEntity = professeurEntity;
    }
    public EnseignementEntity(int id, ProfesseurEntity professeurEntity) {
        this.id = id;
        this.professeurEntity = professeurEntity;
    }
   
    
    
}
