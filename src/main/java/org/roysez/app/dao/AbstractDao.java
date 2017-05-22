package org.roysez.app.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * An abstract base class for Hibernate DAO classes.
 * @param PK the type of primary key  ;
 * @param T the class which this DAO manage  ;
 *
 * Created by roysez on 27.04.2017.
 * 23:50
 * Package : org.roysez.app.dao
 */
 public abstract class AbstractDao<PK extends Serializable, T> {

    /**
     * Set the entity class managed by this DAO.
     */
    private final Class<T> persistentClass;



    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>)
                ((ParameterizedType) this.getClass().getGenericSuperclass())
                        .getActualTypeArguments()[1];
    }


    /**
     * Autowired by spring container ;
     * Configurations for SessionFactory at {@link data-config.xml}
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Method to get current session ;
     * @return the current Session.
     */
    protected Session getSession(){
       return sessionFactory.getCurrentSession();
    }


    /**
     *  Return the persistent instance of <T> with the given key,
     *  or null if there is no such persistent instance.
     * @param key - unique id ;
     * @return object of type <T> ;
     */
    @SuppressWarnings("unchecked")
    protected T getByKey(PK key){
        return (T) getSession().get(persistentClass, key);
    }


    /**
     * Save the given instance.
     * @param entity - entity to save;
     */
    protected void persist(T entity){
        getSession().persist(entity);
    }


    /**
     * Either save or update the given instance,
     * depending upon resolution of the unsaved-value checks
     * (see the manual for discussion of unsaved-value checking).
     * @param entity - entity to save/update;
     */
    protected void update(T entity){ getSession().saveOrUpdate(entity); }


    /**
     * Delete the given instance.
     * @param entity - entity to delete.
     */
    protected void delete(T entity){
        getSession().delete(entity);
    }


    /**
     * Creates a new Criteria query for <E>.
     * @return a new Criteria query.
     */
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
}
