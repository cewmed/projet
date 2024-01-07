package com.ism.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.ism.entities.ModuleEntity;
import com.ism.repositories.DataBase;
import com.ism.repositories.ModuleReposytory;

public class ModuleImpl  implements ModuleReposytory{
    private DataBase dataBase;
    private final String SQL_INSERT="INSERT INTO `module`(`libelle`) VALUES (?)";
    private final String SQL_SELECT_ALL="SELECT * FROM `module` WHERE isArchived = false";
    private final String SQL_UPDATE="UPDATE `module` SET `libelle`= ? WHERE id = ?";
    private final String  SQL_ARCHIVER="UPDATE `module` SET `isArchived`= 1 WHERE id= ?";
// Module by classe SELECT DISTINCT libelle FROM module m, classe c WHERE  m.classe_id = 2;


    public ModuleImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }


    @Override
    public int insert(ModuleEntity data) {
        int lastInsertId=0;
            try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_INSERT);
                dataBase.getPs().setString(1, data.getLibelle());
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
    public int update(ModuleEntity data) {
        int ligne=0;
        try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_UPDATE);
                dataBase.getPs().setString(1,data.getLibelle());
                dataBase.getPs().setInt(2,data.getId());
                // dataBase.getPs().setInt(4,data.getModule_id());
                 ligne=dataBase.executeUpdate();
                  if (ligne > 0) {
                        System.out.println("Le module a été mise à jour avec succès.");
                  }else{
                        System.out.println("Aucun module n'a été mise à jour.");      
                  }
                  dataBase.closeConnexion();
                
             } catch (SQLException e) {
                e.printStackTrace();
            }
        return ligne;
    }





    @Override
    public ArrayList<ModuleEntity> findAll() {
         ArrayList<ModuleEntity> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_SELECT_ALL);
                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        ModuleEntity data=new ModuleEntity( resultSet.getInt("id")
                                         , resultSet.getString("libelle")
                                         );

                         datas.add(data);
                           
                      }
                   resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {
                    System.out.println("Erreur execution de Requete");
                }
            
            return datas;
    }


    @Override
    public int archiver(ModuleEntity data) {
        int ligne=0;
        try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_ARCHIVER);
               
                dataBase.getPs().setInt(1,data.getId());
  
                 ligne=dataBase.executeUpdate();
                  if (ligne > 0) {
                        System.out.println("Le module  a été archiver.");
                  }else{
                        System.out.println("Aucun module n'a été archiver.");      
                  }
                  dataBase.closeConnexion();
                
             } catch (SQLException e) {
                e.printStackTrace();
            }
        return ligne;
    }


    @Override
    public int reinitialiser(ModuleEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reinitialiser'");
    }
    
}
