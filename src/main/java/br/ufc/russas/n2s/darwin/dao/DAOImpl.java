/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Wallison Carlos
 * @param <T>
 */
@Repository("daoImpl")
public class DAOImpl<T> implements DAOIfc<T>{
   
    private SessionFactory sessionFactory;
    
    public DAOImpl(){}
    
    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public T adiciona(T object) {
        Session session = getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try{
            if(object != null){
                session.persist(object);
                t.commit();
                return object;
            }else{
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    
    @Override
    public T atualiza(T object) {
        Session session = getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try{
            if(object != null){
                session.update(object);
                t.commit();
                return object;
            }else{
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    
    @Override
    public void remove(T object) {
        Session session = getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try{
            if(object != null){
                session.delete(object);
                t.commit();
            }else{
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    

    @Override
    public List<T> lista(Class object) {
        Session session = getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try{
            List<T> objects = Collections.synchronizedList(session.createCriteria(object.getClass()).list());
            t.commit();
            return objects;
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    
    @Override
    public T getObject(Class<T> classe, long codObject) {
        Session session = getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try{
            T object = (T) session.get(classe, codObject);
            t.commit();
            return object;
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
}