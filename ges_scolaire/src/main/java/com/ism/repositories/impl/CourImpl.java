package com.ism.repositories.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.ism.entities.CourEntity;
import com.ism.repositories.CourReposytory;
import com.ism.repositories.DataBase;

public class CourImpl implements CourReposytory{
    private DataBase dataBase;
    private final String SQL_INSERT="INSERT INTO `cour`( `libelle`, `salle_de_cour`, `date`, `debut_heure`, `fin_heure`) VALUES (?,?,?,?,?)";
    private final String SQL_FIND_BY="SELECT c.* FROM cour c INNER JOIN planning p ON p.id = c.id JOIN classe cl ON cl.id = p.classe_id WHERE cl.id = ?";
    public CourImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public int insert(CourEntity data) {
         int lastInsertId=0;
            try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_INSERT);
                dataBase.getPs().setString(1,data.getLibelle());
                dataBase.getPs().setString(2,data.getSalleDeCour());
                LocalDate localDate = data.getDate();
                java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                dataBase.getPs().setDate(3, sqlDate);
                LocalTime heure = data.getDebutHeure();
                LocalTime heureFin = data.getFinHeure();
                java.sql.Time heureDebut = java.sql.Time.valueOf(heure);
                dataBase.getPs().setTime(4, heureDebut);
                java.sql.Time endHour = java.sql.Time.valueOf(heureFin);
                dataBase.getPs().setTime(5,endHour);
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
    public int update(CourEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int archiver(CourEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiver'");
    }

    @Override
    public int reinitialiser(CourEntity data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reinitialiser'");
    }

    @Override
    public ArrayList<CourEntity> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public ArrayList<CourEntity> findCoursByClasse(int id) {
        ArrayList<CourEntity> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_FIND_BY);
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
                                                        finHeure.toLocalTime()
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
    
}
