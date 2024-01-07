package com.ism.entities;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleEntity {
    public ModuleEntity(int int1, String string, int int2) {
    }
    private int id;
    private String libelle;
    private Boolean isArchived;
    private ClasseEntity classeEntity;
    public ModuleEntity(String libelle) {
        this.libelle = libelle;
    }
    public ModuleEntity(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
    public ModuleEntity(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        // return String.format("| %-3s | %-8s |",
        // id, libelle);
        return ""+id +"\t" + libelle + "\n"+"\n";
        // return "id\n" + id + ", libelle\n" + libelle + "\n\n";
    }

}
