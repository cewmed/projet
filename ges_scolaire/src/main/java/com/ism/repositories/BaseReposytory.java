package com.ism.repositories;

import java.util.ArrayList;

public interface BaseReposytory<T> {
            
    int insert(T data);
    int update(T data);
    int archiver(T data);
    int reinitialiser(T data);
    ArrayList<T> findAll();

}
