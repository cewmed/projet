package com.ism.services;



public interface BaseService<T> {
    boolean modification(T data);
    boolean archiver(T data);

    T find(int id);
}
