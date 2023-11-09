package com.ism.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalleEntity {
    private int id;
    private String libelle;
    private int place;
    private boolean isArchived;
    public SalleEntity(int id, String libelle, int place) {
        this.id = id;
        this.libelle = libelle;
        this.place = place;
    }
    @Override
    public String toString() {
        return String.format("| %-3s | %-20s | %-10s|",
        id, libelle, place);
    }
}
