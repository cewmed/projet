package com.ism.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ism.entities.EnseignementEntity;
import com.ism.repositories.DataBase;
import com.ism.repositories.EnseignementReposytory;

public class EnseignementImpl implements EnseignementReposytory{

    private final String SQL_INSERT="INSERT INTO `enseignement`( `classe_id`, `module_id`, `prof_id`) VALUES (?,?,?)";
private DataBase dataBase;   

    public EnseignementImpl(DataBase dataBase) {
    this.dataBase = dataBase;
}

    @Override
    public int insert(EnseignementEntity data) {
                int lastInsertId=0;
            try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_INSERT);
                dataBase.getPs().setInt(1,data.getClasse_id());
                dataBase.getPs().setInt(2,data.getModule_id());
                dataBase.getPs().setInt(3,data.getProf_id());
                
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
    public int update(EnseignementEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ArrayList<EnseignementEntity> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
}
