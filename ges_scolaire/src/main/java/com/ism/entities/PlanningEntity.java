package com.ism.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlanningEntity {
    private int id;
    private int classe_id;
    private int cour_id;
    private int prof_id;
}
