package bstorm.akimts.exohotel.services.impl;

import bstorm.akimts.exohotel.exceptions.EntityNotFoundException;
import bstorm.akimts.exohotel.models.entities.Room;
import bstorm.akimts.exohotel.repositories.RoomRepository;
import bstorm.akimts.exohotel.exceptions.NullArgumentException;
import bstorm.akimts.exohotel.models.entities.Amenities;
import bstorm.akimts.exohotel.services.RoomService;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.time.LocalDate;
import java.util.List;

@Singleton
public class RoomServiceImpl implements RoomService {

    @EJB
    private RoomRepository repository;

    @Override
    public Room getOne(Long id) {
        return repository.getOne(id)
                .orElseThrow(() -> new EntityNotFoundException(Room.class, id));
    }

    @Override
    public List<Room> getAll() {
        return repository.getAll();
    }

    @Override
    public void insert(Room toInsert) {
        if(toInsert == null)
            throw new NullArgumentException();

        toInsert.setId(0L);
        repository.save(toInsert);
    }

    @Override
    public void delete(Long id) {
        if(id == null)
            throw new NullArgumentException();

        repository.delete(id);
    }

    @Override
    public void update(Room toUpdate) {
        if(toUpdate == null)
            throw new NullArgumentException();

        if( !repository.exists(toUpdate.getId()) )
            throw new EntityNotFoundException(Room.class, toUpdate.getId());

        repository.save(toUpdate);
    }

    @Override
    public List<Room> getAvailable(LocalDate begin, LocalDate end) {
        return null;
    }

    @Override
    public List<Room> getWithAmenities(Amenities... amenities) {
        return null;
    }
}
