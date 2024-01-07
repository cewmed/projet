package com.ism.repositories;

import java.util.ArrayList;

import com.ism.entities.CourEntity;

public interface CourReposytory extends BaseReposytory<CourEntity>{
     ArrayList<CourEntity> findCoursByClasse(int id);
}
