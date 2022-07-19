package bstorm.akimts.exohotel.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private final Class<?> entityClass;
    private final Object id;

    public EntityNotFoundException(Class<?> entityClass, Object id) {
        super("Element of class " +entityClass+ " {id: "+id+"}"+ " was not found in the database");
        this.entityClass = entityClass;
        this.id = id;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public Object getId() {
        return id;
    }
}
