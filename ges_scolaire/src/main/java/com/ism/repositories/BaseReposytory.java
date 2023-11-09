package com.ism.repositories;

import java.util.ArrayList;

public interface BaseReposytory<T> {
            
    int insert(T data);
    int update(T data);
    ArrayList<T> findAll();

}
