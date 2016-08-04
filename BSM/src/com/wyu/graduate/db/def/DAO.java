package com.wyu.graduate.db.def;

import java.io.Serializable;
import java.util.List;

public interface DAO<T> {



    int insert(Class<T> clazz ,T t);


    int delete(Serializable id);


    boolean update(Class<T>calzz,int id,T t);

   
    T find(Class<T> clazz, int id);

 
    T find(Class<T> clazz, String user);
    
    List<T> findBySelection(Class<T> clazz,String selection,String value);
    
    List<T> findBySelection(Class<T> clazz,String selection1,String value1, String selection2, String value2);
  
    List<T> findAll(Class<T> clazz,int startPos,int num);


    long count();


}
