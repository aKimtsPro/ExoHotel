package bstorm.akimts.exohotel.repositories.impl;

import bstorm.akimts.exohotel.models.entities.BaseEntity;
import bstorm.akimts.exohotel.repositories.BasicRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends BaseEntity<TID>, TID extends Serializable> implements BasicRepository<T, TID> {

    @PersistenceContext
    private EntityManager manager;
    private final Class<T> entityClass;

    protected AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Saves the given entity.
     * If the id is null, insert
     * else, update
     *
     * @param toSave, the entity to save
     * @return the saved entity
     * @throws IllegalArgumentException if the given arg. is null
     */
    @Override
    public T save(T toSave) {
        if( toSave == null )
            throw new IllegalArgumentException("saved entity cannot be null");

        manager.getTransaction().begin();
        toSave = manager.merge(toSave);
        manager.getTransaction().commit();
        return toSave;
    }

    @Override
    public Optional<T> getOne(TID id) {
        Optional<T> opt = Optional.ofNullable( manager.find(entityClass, id) );
        opt.ifPresent( manager::detach );
        return opt;
    }

    @Override
    public T delete(TID id) {
        T toDelete = manager.find(entityClass, id);
        if( toDelete != null )
            manager.remove( toDelete );
        return toDelete;
    }

    @Override
    public List<T> getAll() {
        return manager.createQuery( "SELECT t FROM " + entityClass.getSimpleName() + " t", entityClass)
                .getResultList();
    }

    @Override
    public boolean exists(TID id) {
        return 0 < manager.createQuery("SELECT COUNT(t) FROM Room t", Integer.class).getSingleResult();
    }

    protected EntityManager getEntityManager(){
        return manager;
    }

}
