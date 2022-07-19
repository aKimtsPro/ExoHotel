package bstorm.akimts.exohotel.repositories.impl;

import bstorm.akimts.exohotel.models.entities.Room;

import javax.ejb.Singleton;

@Singleton
public class RoomRepositoryImpl extends AbstractRepository<Room, Long> {
    public RoomRepositoryImpl() {
        super(Room.class);
    }
}
