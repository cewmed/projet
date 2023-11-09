package com.ism.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ism.entities.ClasseEntity;
import com.ism.entities.ModuleEntity;
import com.ism.entities.ProfesseurEntity;
import com.ism.repositories.DataBase;
import com.ism.repositories.ProfesseurReposytory;

public class ProfesseurImpl implements ProfesseurReposytory {
     private final String SQL_INSERT="INSERT INTO `professeur`(`nom_complet`, `email`, `specialite`) VALUES (?,?,?)";
     private final String SQL_SELECT_MOD_CLASSE_BY_PROF="SELECT classe.nom,module.libelle FROM enseignement e JOIN professeur ON e.prof_id = professeur.id JOIN classe ON e.classe_id = classe.id JOIN module ON e.module_id = module.id WHERE professeur.id = ?";
     private final String SQL_SELECT_ALL="SELECT * FROM `professeur`";
     private final String SQL_SELECT_FIND_BY="SELECT * FROM `professeur` WHERE id = ?";

    
    // private final String  SQL_UPDATE="SELECT * FROM classe";

    private DataBase dataBase;

    public ProfesseurImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public int insert(ProfesseurEntity data) {
        int lastInsertId=0;
            try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_INSERT);
                dataBase.getPs().setString(1,data.getNomComplet());
                dataBase.getPs().setString(2,data.getEmail());
                dataBase.getPs().setString(3,data.getSpecialite());
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
    public int update(ProfesseurEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ArrayList<ProfesseurEntity> findAll() {
        ArrayList<ProfesseurEntity> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_SELECT_ALL);
                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        ProfesseurEntity data=new ProfesseurEntity( resultSet.getInt("id")
                                         , resultSet.getString("nom_complet")
                                         ,resultSet.getString("email")
                                          ,resultSet.getString("specialite")
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
    public ArrayList<Object> showDataByProfId(int idProf) {
    //    ArrayList<ClasseEntity> tabClasses=new ArrayList<>();
    //    ArrayList<ModuleEntity> tabmodules=new ArrayList<>();
       ArrayList<Object> obj=new ArrayList<>();
       try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_SELECT_MOD_CLASSE_BY_PROF);
                        dataBase.getPs().setInt(1,idProf);
                        ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        ClasseEntity data=new ClasseEntity( resultSet.getString("nom"));             
                        ModuleEntity dataModule=new ModuleEntity( resultSet.getString("libelle"));             

                         obj.add(data);
                         obj.add(dataModule);
                           
                      }
                   resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erreur execution de Requete");
                }
       return obj;

    }

    @Override
    public ArrayList<ProfesseurEntity> findById(int id) {
        ArrayList<ProfesseurEntity> obj=new ArrayList<>();
       try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_SELECT_FIND_BY);
                        dataBase.getPs().setInt(1,id);
                        ResultSet resultSet=dataBase.executeSelect();
                    if (resultSet.next()) {
                        ProfesseurEntity prof=new ProfesseurEntity(resultSet.getInt("id"),
                                                                    resultSet.getString("nom_complet"),
                                                                    resultSet.getString("email"),
                                                                    resultSet.getString("specialite"));

                         obj.add(prof);                           
                      }
                   resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erreur execution de Requete");
                }
       return obj;
    }
    
}
