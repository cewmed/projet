package com.ism.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ism.entities.PlanningEntity;
import com.ism.repositories.DataBase;
import com.ism.repositories.PlanningReposytory;
import lombok.AllArgsConstructor;
@AllArgsConstructor

public class PlanningImpl implements PlanningReposytory{
    private final String SQL_INSERT="INSERT INTO `planning`(`classe_id`, `cour_id`, `professeur_id`) VALUES (?,?,?)";
    private DataBase dataBase;
    @Override
    public int insert(PlanningEntity data) {
        int lastInsertId=0;
            try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_INSERT);
                dataBase.getPs().setInt(1,data.getClasse_id());
                dataBase.getPs().setInt(2,data.getCour_id());
                dataBase.getPs().setInt(3,data.getProf_id());
                // dataBase.getPs().setInt(4,data.getModule_id());
                  dataBase.executeUpdate();
                  ResultSet rs=  dataBase.getPs().getGeneratedKeys();
                if(rs.next()){
                  lastInsertId=rs.getInt(1) ; 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
          
            return lastInsertId; 
    }

    @Override
    public int update(PlanningEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int archiver(PlanningEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiver'");
    }

    @Override
    public int reinitialiser(PlanningEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reinitialiser'");
    }

    @Override
    public ArrayList<PlanningEntity> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
     
}
