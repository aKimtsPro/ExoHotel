package bstorm.akimts.exohotel.services;

import bstorm.akimts.exohotel.models.entities.Room;
import bstorm.akimts.exohotel.models.entities.Amenities;

import java.time.LocalDate;
import java.util.List;

public interface RoomService extends CrudService<Room, Long>{

    public List<Room> getAvailable(LocalDate begin, LocalDate end);
    public List<Room> getWithAmenities(Amenities... amenities);

}
