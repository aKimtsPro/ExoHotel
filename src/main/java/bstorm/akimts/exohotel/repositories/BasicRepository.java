package bstorm.akimts.exohotel.repositories;

import bstorm.akimts.exohotel.models.entities.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BasicRepository<T extends BaseEntity<TID>, TID extends Serializable> {

    public Optional<T> getOne(TID id);
    public List<T> getAll();

    public T save(T toSave);

    public T delete (TID id);

    public boolean exists(TID id);

}
