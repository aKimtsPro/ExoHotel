package bstorm.akimts.exohotel.services;

import bstorm.akimts.exohotel.models.entities.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T extends BaseEntity<TID>, TID extends Serializable> {

    public T getOne(TID id);
    public List<T> getAll();

    public void insert(T toInsert);

    public void delete(TID id);

    public void update(T toUpdate);

}
