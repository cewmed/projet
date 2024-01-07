package com.ism.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ism.entities.ClasseEntity;
import com.ism.entities.CourEntity;
import com.ism.entities.ModuleEntity;
import com.ism.entities.ProfesseurEntity;
import com.ism.repositories.DataBase;
import com.ism.repositories.ProfesseurReposytory;

public class ProfesseurImpl implements ProfesseurReposytory {
     private final String SQL_INSERT="INSERT INTO `professeur`(`nom_complet`, `email`, `specialite`) VALUES (?,?,?)";
     private final String SQL_SELECT_MOD_CLASSE_BY_PROF="SELECT classe.nom,module.libelle FROM enseignement e JOIN professeur ON e.prof_id = professeur.id JOIN classe ON e.classe_id = classe.id JOIN module ON e.module_id = module.id WHERE professeur.id = ?";
     private final String SQL_SELECT_ALL="SELECT * FROM `professeur` WHERE isArchived = false";
     private final String SQL_SELECT_FIND_BY="SELECT * FROM `professeur` WHERE id = ? AND isArchived = 0";
     private final String  SQL_ARCHIVER="UPDATE `professeur` SET `isArchived`= 1 WHERE id= ?";
     private final String  SQL_PROF_BY_MODULE_ID="SELECT * FROM professeur p JOIN enseignement e ON e.prof_id = p.id JOIN module m on m.id = e.module_id WHERE m.id = ? AND p.isArchived = 0";
    
    private final String  SQL_UPDATE="UPDATE `professeur` SET `nom_complet`=?,`email`=?,`specialite`=? WHERE id = ?";
    private final String  SQL_COURS_BY_PROF="SELECT c.* FROM cour c INNER JOIN planning p ON c.id = p.cour_id WHERE p.professeur_id = ?";

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
        int ligne=0;
        try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_UPDATE);
                dataBase.getPs().setString(1,data.getNomComplet());
                dataBase.getPs().setString(2,data.getEmail());
                dataBase.getPs().setString(3,data.getSpecialite());
                dataBase.getPs().setInt(4,data.getId());
                // dataBase.getPs().setInt(4,data.getModule_id());
                 ligne=dataBase.executeUpdate();
                  if (ligne > 0) {
                        System.out.println("Ce Professeur a été mise à jour avec succès.");
                  }else{
                        System.out.println("Aucun Professeur n'a été mise à jour.");      
                  }
                  dataBase.closeConnexion();
                
             } catch (SQLException e) {
                e.printStackTrace();
            }
        return ligne;
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

                         obj.add(data.getNom());
                         obj.add(dataModule.getLibelle());
                           
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

    @Override
    public int archiver(ProfesseurEntity data) {
        int ligne=0;
        try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_ARCHIVER);
               
                dataBase.getPs().setInt(1,data.getId());
  
                 ligne=dataBase.executeUpdate();
                  if (ligne > 0) {
                        // System.out.println(" ");
                  }else{
                        System.out.println("Aucun Professeur n'a été archiver.");      
                  }
                  dataBase.closeConnexion();
                
             } catch (SQLException e) {
                e.printStackTrace();
            }
        return ligne;
    }

    @Override
    public int reinitialiser(ProfesseurEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reinitialiser'");
    }

    @Override
    public ArrayList<ProfesseurEntity> findProfByModuleId(int id) {
        ArrayList<ProfesseurEntity> professeurs=new ArrayList<>();
       try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_PROF_BY_MODULE_ID);
                        dataBase.getPs().setInt(1,id);
                        ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                         ProfesseurEntity prof=new ProfesseurEntity(resultSet.getInt("id"),
                                                                    resultSet.getString("nom_complet"),
                                                                    resultSet.getString("email"),
                                                                    resultSet.getString("specialite"));
                         professeurs.add(prof);
    
                      }
                   resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erreur execution de Requete");
                }
       return professeurs;
    }

    @Override
    public ArrayList<CourEntity> findCoursByProf(int id) {
       ArrayList<CourEntity> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_COURS_BY_PROF);
                          dataBase.getPs().setInt(1,id);
                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        java.sql.Time debutHeure = resultSet.getTime("debut_heure");
                        java.sql.Time finHeure = resultSet.getTime("fin_heure");
                        CourEntity data=new CourEntity( resultSet.getInt("id"),
                                                        resultSet.getString("libelle"),
                                                        resultSet.getString("salle_de_cour"),
                                                        resultSet.getDate("date").toLocalDate(),
                                                        debutHeure.toLocalTime(),
                                                        finHeure.toLocalTime());          

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
    
}
