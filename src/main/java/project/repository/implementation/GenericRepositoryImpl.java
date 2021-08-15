package project.repository.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import project.ObjectFactory;
import project.repository.GenericRepository;


import java.io.Serializable;

public abstract class GenericRepositoryImpl<T, ID extends Serializable> implements GenericRepository<T, ID> {

    private final SessionFactory sessionFactory;
    private Class<T> entity;

    public GenericRepositoryImpl(Class<T> entity) {
        this.entity = entity;
        sessionFactory  = ObjectFactory.getObjectFactory().getSessionFactory();
    }

    @Override
    public T save(T o){
        Transaction tx = null;
        try(Session session = getSession()){
            tx = session.getTransaction();
            tx.begin();
            session.save(o);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            System.err.println(e.getMessage());
        }

        return o;
    }

    @Override
    public T update(T o){
        Transaction tx = null;
        try(Session session = getSession()){
            tx = session.getTransaction();
            tx.begin();
            session.update(o);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            System.err.println(e.getMessage());
        }
        return o;
    }

    @Override
    public T get(ID id){
        try(Session session = getSession()){
            return session.get(entity, id);
        }
    }

    @Override
    public void remove(T o){
        try(Session session = getSession()){
            session.delete(o);
        }
    }

    @Override
    public T load(ID id){
        try(Session session = getSession()){
            return session.load(entity, id);
        }
    }

    protected Session getSession(){
        return sessionFactory.openSession();
    }
}
