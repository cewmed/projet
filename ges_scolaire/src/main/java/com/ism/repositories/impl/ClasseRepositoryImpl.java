package com.ism.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ism.entities.ClasseEntity;
import com.ism.entities.ModuleEntity;
import com.ism.repositories.ClasseRepository;
import com.ism.repositories.DataBase;

public class ClasseRepositoryImpl implements ClasseRepository {
    private final String SQL_INSERT="INSERT INTO `classe` (`nom`,  `filiere`,  `niveau`) VALUES (?,?,?)";
    private final String SQL_SELECT_MODULE_FROM_CLASSE="SELECT m.id,m.libelle FROM module m INNER JOIN affectation a ON m.id = a.module_id WHERE a.classe_id = ?";
    private final String SQL_SELECT_CLASSE_FROM_MODULE="SELECT c.* FROM classe c INNER JOIN affectation a ON a.classe_id=c.id WHERE a.module_id = ?";
    private final String SQL_UPDATE="UPDATE `classe` SET `nom`= ?,`filiere`= ?,`niveau`= ? WHERE id = ?";

    private final String  SQL_SELECT_ALL="SELECT * FROM classe WHERE isArchived = false";
    private final String  SQL_ARCHIVER="UPDATE `classe` SET `isArchived`= 1 WHERE id= ?";
    private final String  SQL_DE_ARCHIVER="UPDATE `classe` SET `isArchived`= 0 WHERE id= ?";

    private DataBase dataBase;
    public ClasseRepositoryImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public int insert(ClasseEntity data) {
        int lastInsertId=0;
            try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_INSERT);
                dataBase.getPs().setString(1,data.getNom());
                dataBase.getPs().setString(2,data.getFiliere());
                dataBase.getPs().setString(3,data.getNiveau());
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
    public ArrayList<ClasseEntity> findAll() {
        ArrayList<ClasseEntity> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_SELECT_ALL);
                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        ClasseEntity data=new ClasseEntity( resultSet.getInt("id")
                                         , resultSet.getString("nom")
                                         ,resultSet.getString("filiere")
                                          ,resultSet.getString("niveau")
                                        //   ,resultSet.getInt("module_id")
                                          );
                      

                         datas.add(data);
                           
                      }
                   resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erreur execution de Requete");
                }
            
            return datas;
    }

    @Override
    public int update(ClasseEntity data) {
        int ligne=0;
        try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_UPDATE);
                dataBase.getPs().setString(1,data.getNom());
                dataBase.getPs().setString(2,data.getFiliere());
                dataBase.getPs().setString(3,data.getNiveau());
                dataBase.getPs().setInt(4,data.getId());
                // dataBase.getPs().setInt(4,data.getModule_id());
                 ligne=dataBase.executeUpdate();
                  if (ligne > 0) {
                        System.out.println("La classe a été mise à jour avec succès.");
                  }else{
                        System.out.println("Aucune classe n'a été mise à jour.");      
                  }
                  dataBase.closeConnexion();
                
             } catch (SQLException e) {
                e.printStackTrace();
            }
        return ligne;
    }

    @Override
    public ArrayList<ModuleEntity> findModuleFromClasse(int id) {
        ArrayList<ModuleEntity> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_SELECT_MODULE_FROM_CLASSE);
                        dataBase.getPs().setInt(1,id);
                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        ModuleEntity data=new ModuleEntity(
                            resultSet.getInt("id"),
                            resultSet.getString("libelle")
                            );

                         datas.add(data);
                           
                      }
                   resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erreur execution de Requete");
                }
            
            return datas;
    }

    @Override
    public ArrayList<ClasseEntity> findAllClassesByModule(int idModule) {
         ArrayList<ClasseEntity> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_SELECT_CLASSE_FROM_MODULE);
                         dataBase.getPs().setInt(1,idModule);
                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        ClasseEntity data=new ClasseEntity( resultSet.getInt("id")
                                         , resultSet.getString("nom")
                                         ,resultSet.getString("filiere")
                                          ,resultSet.getString("niveau")

                                          );

                         datas.add(data);
                           
                      }
                   resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erreur execution de Requete");
                }
            
            return datas;
    }

    @Override
    public int archiver(ClasseEntity data) {
        int ligne=0;
        try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_ARCHIVER);
               
                dataBase.getPs().setInt(1,data.getId());
  
                 ligne=dataBase.executeUpdate();
                  if (ligne > 0) {
                        System.out.println("La classe a été archiver.");
                  }else{
                        System.out.println("Aucune classe n'a été archiver.");      
                  }
                  dataBase.closeConnexion();
                
             } catch (SQLException e) {
                e.printStackTrace();
            }
        return ligne;
    }

    @Override
    public int reinitialiser(ClasseEntity data) {
       int ligne=0;
        try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_DE_ARCHIVER);
               
                dataBase.getPs().setInt(1,data.getId());
  
                 ligne=dataBase.executeUpdate();
                  if (ligne > 0) {
                        System.out.println("La classe a été reinitialiser.");
                  }else{
                        System.out.println("Aucune classe n'a été reinitialiser.");      
                  }
                  dataBase.closeConnexion();
                
             } catch (SQLException e) {
                e.printStackTrace();
            }
        return ligne;
    }

   
    

  
    
}
